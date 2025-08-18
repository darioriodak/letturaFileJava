package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UtenteDAO;
import dao.UtenteDAOImpl;
import entity.Utente;
import security.BCrypt;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtenteDAO utenteDAO;
	
       
    
    public AuthServlet() {
        super();
    }
    
    public void init() throws ServletException {
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	String ip = getInitParameter("ip");
		String port = getInitParameter("port");
		String dbName = getInitParameter("dbName");
		String userName = getInitParameter("userName");
		String passWord = getInitParameter("passWord");
		
		
		
		this.utenteDAO = new UtenteDAOImpl(ip,port,dbName,userName,passWord);	
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
        	response.setStatus(404);
            response.getWriter().append("Endpoint mancante (es. /register o /login)");
            return;
        }

        // Leggi il corpo della richiesta JSON
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();

        try {
            switch (pathInfo) {
                case "/register":
                    gestisciRegistrazione(jsonString, response);
                    break;
                case "/login":
                    gestisciLogin(jsonString, request, response);
                    break;
                default:
                    response.setStatus(209);
                    response.getWriter().append("Errore, endpoind malformato");
                    return;
            }
        } catch (JSONException e) {
            response.setStatus(404);
            response.getWriter().append("Errore, JSON Malformato");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(500);
            response.getWriter().append("Errore del database");
            return;          
        }
		
		
		
	}
	
	
	private void gestisciRegistrazione(String jsonString, HttpServletResponse response) throws JSONException, SQLException, IOException {
        JSONObject json = new JSONObject(jsonString);
        String email = json.getString("email");
        String password = json.getString("password");

        // Validazione base
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
        	response.setStatus(400);
            response.getWriter().append("Email e password sono obbligatori.");
            return;
        }
        
        // Controlla se l'utente esiste già
        if (utenteDAO.findByEmail(email) != null) {
        	response.setStatus(409);
            response.getWriter().append("Un utente con questa email esiste già."); 
            return;
        }

        // Crea l'hash della password
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

        // Crea e salva il nuovo utente
        Utente nuovoUtente = new Utente(email,passwordHash);
        
        int res = -1;
        res = utenteDAO.salva(nuovoUtente);
        
        if(res > 0) {
        	response.setStatus(201);
            response.getWriter().append("Utente registrato con successo.");
        	
        }
	}
        
    private void gestisciLogin(String jsonString, HttpServletRequest request, HttpServletResponse response) throws JSONException, SQLException, IOException {
            
        	JSONObject json = new JSONObject(jsonString);
            String email = json.getString("email");
            String password = json.getString("password");

            Utente utente = utenteDAO.findByEmail(email);

            // Controlla se l'utente esiste e se la password corrisponde
            if (utente == null || !BCrypt.checkpw(password, utente.getPasswordHash())) {
                response.setStatus(401);
                response.getWriter().append("Utente inesistente o password sbagliata");
                return;
            }

            // Login riuscito: crea la sessione
            HttpSession session = request.getSession(true);
            session.setAttribute("user", utente);
            
            response.setStatus(200);
            response.getWriter().append("Login effettuato con successo.");
    }
        
}


