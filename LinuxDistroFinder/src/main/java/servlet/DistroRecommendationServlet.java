package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.GestoreDistro;
import service.GestoreDistroImpl;
import dao.DistroDAO;
import dao.DistroDAOImpl;
import dao.InstallazioneDAO;
import dao.InstallazioneDAOImpl;
import dao.ProfiloHardwareDAO;
import dao.ProfiloHardwareDAOImpl;
import dao.UtenteDAO;
import dao.UtenteDAOImpl;
import entity.Distro;
import entity.InfoUtente;
import entity.Installazione;
import entity.ListaDistro;
import entity.ProfiloHardware;
import entity.Utente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.json.JSONException;


public class DistroRecommendationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DistroDAO distroDAO;
	ProfiloHardwareDAO profiloHardwareDAO;
	InstallazioneDAO installazioneDAO;
	UtenteDAO utenteDAO;
	GestoreDistro gestoreDistro;
    
    public DistroRecommendationServlet() {
        super();
    }
    
    public void init() {
    	String ip = getInitParameter("ip");
		String port = getInitParameter("port");
		String dbName = getInitParameter("dbName");
		String userName = getInitParameter("userName");
		String passWord = getInitParameter("passWord");
		
		//creazione di tutti i DAO + service layer 
		// loose coupling viene dichiarato il tipo di intefaccia e assegnato all'implementazione
		// permette di usare l'interfaccia come contratto e l'implementazione cambia ma rispetta il contratto
    	this.distroDAO = new DistroDAOImpl(ip,port,dbName,userName,passWord);
    	this.profiloHardwareDAO = new ProfiloHardwareDAOImpl(ip,port,dbName,userName,passWord);
    	this.installazioneDAO = new InstallazioneDAOImpl(ip,port,dbName,userName,passWord);
    	this.utenteDAO = new UtenteDAOImpl(ip,port,dbName,userName,passWord);
    	this.gestoreDistro = new GestoreDistroImpl(distroDAO);
    	
    	
    	
    }
/*parametro json nell url (i simboli tipo { } "" ' vanno correttamente convertiti via client  */
	// da rivedere il metodo doGet inserire le eccezioni di collegamento al database
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Invocando un metodo doGet");
		PrintWriter out = response.getWriter();
		Distro distro = null;
		ListaDistro listaDistro = new ListaDistro();
		
		 
		if(request.getParameter("nome") == null  && request.getParameter("id") == null) {
		
				try {
					listaDistro = distroDAO.caricaTutteDistro();
						
				} catch (SQLException e) {
					e.printStackTrace();
					response.setStatus(500);
					response.getWriter().append("errore di connessione al database");
					return;
					}
				if(!listaDistro.isEmpty()) {
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					out.print(listaDistro.toJSONString());
					out.flush();
					response.setStatus(200);
					return;
				}else {
					response.setStatus(200);
					response.getWriter().append("non esistono distribuzioni salvate nel database");
					return;
				}	
				
		}
		int id = -1; 
		
		if(request.getParameter("id") != null) {
			try {
				id = Integer.valueOf(request.getParameter("id"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.setStatus(492);
				response.getWriter().append("id non processabile");
				return;
			}
			try {
				distro = distroDAO.caricaDistroById(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(distro != null ) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(distro.toJSONString());
				out.flush();
				return;	
			}else {
				response.setStatus(551);
				response.getWriter().append("non esistono distribuzioni con il parametro specificato");
				return;
			}
			
		}
		else {
			
			String nome = request.getParameter("nome");
			try {
				listaDistro = distroDAO.caricaDistroByName(nome);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (!listaDistro.isEmpty()) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(listaDistro.toJSONString());
				out.flush();	
			}else {
				response.setStatus(552);
				response.getWriter().append("non esistono distribuzioni con il parametro specificato");
				return;
			}
				
		}
			
	}

	//inserisce un nuovo utente con il suo hardware nel database lo collega alla distribuzione consigliata e invia la risposta al client
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		System.out.println("Invocando un metodo doPost");
		
        try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) { // Legge il corpo della richiesta
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        } catch (IOException e) {
            System.err.println("Errore nella lettura del corpo della richiesta POST: " + e.getMessage());
            response.setStatus(500); 
            return;
        }

        String jsonString = jsonBuffer.toString();
        // fine lettura della richiesta 
        
        HttpSession session = request.getSession(false);
        Utente utenteLoggato; // Dichiari la variabile

        if (session != null) {
            //  Se la sessione esiste, recuperi l'attributo "user"
            utenteLoggato = (Utente) session.getAttribute("user");
        } else {
            //  Se la sessione non esiste, la variabile vale null
            utenteLoggato = null;
        }
        
        try {
			gestisciRichiesta(jsonString,utenteLoggato,request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void gestisciRichiesta(String jsonString,Utente utenteLoggato,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		InfoUtente infoUtente = null;
		Installazione installazione = null;
		ListaDistro listaDistro= new ListaDistro();
		
		// deserializzazione dell'oggetto json
		
		try {
			infoUtente = InfoUtente.fromJSON(jsonString);
		} catch (JSONException e) {
			response.setStatus(422);
			response.getWriter().append("Il file JSON è malformato");
			return;
		}
		
		// creazione dell oggetto profiloHardware relativo
		ProfiloHardware profilo = new ProfiloHardware(infoUtente);
		
		// chiamata al service layer
		if(gestoreDistro != null && profilo != null) {
				try {
					listaDistro = distroDAO.caricaTutteDistro(); // conterrà la raccomandazione tramite algoritmo di raccomandazione
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else {
			response.setStatus(490);
			return;
					
		}
		
		
		// a seconda se l'utente è loggato o no 
		
		if(utenteLoggato != null ) { // inserisco i dati nel database
			
			profilo.setIdUtente(utenteLoggato.getId()); // associo al profilo l'id dell utente loggato
			
			int idProfiloHardware = -1;
			
			try {
				idProfiloHardware = profiloHardwareDAO.salva(profilo);
			} catch (SQLException e) {
				response.setStatus(500);
				response.getWriter().append("Errore di inserimento del profilo Hardware");
				return;
			}
			
			int idDistro = -1;
			
			if(!listaDistro.isEmpty()) {
				idDistro = listaDistro.getFirst().getId();
			}else {
				response.setStatus(491);
				response.getWriter().append("Nessuna raccomandazione disponibile");
				return;
			}
			
			String usecase = infoUtente.getUseCase();
			
			if(idProfiloHardware > 0) {
				installazione = new Installazione(idProfiloHardware,idDistro,usecase);
			}else {
				response.setStatus(500);
				response.getWriter().append("Errore di inserimento del Profilo Hardware");
				return;	
			}
			
			int res = -1;
			try {
				 res = installazioneDAO.salva(installazione);
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
			
			//serializzazione del risultato ottenuto
			
			if(res > 0) {
				response.setStatus(200);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(listaDistro.toJSONString());
				out.flush();
				return;
				
			}else {
				response.setStatus(500);
				response.getWriter().append("Errore di inserimento nel database");
				return;

			}
			
		}else { // se non è loggato non inserisco niente nel db
			response.setStatus(200);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(listaDistro.toJSONString());
			out.flush();
			return;
			
		}
				
			
	}
	
	// opzionale aggiungere metodo doPut che inserisce le infoUtente nella tabella Utente del db 
}
