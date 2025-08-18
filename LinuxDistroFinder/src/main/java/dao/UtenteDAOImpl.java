package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import entity.Utente;

public class UtenteDAOImpl implements UtenteDAO{
	
	String ip;
	String port;
	String dbName;
	String userName;
	String pwd;
	
	
	public UtenteDAOImpl(String ip,String port,String dbName,String userName,String pwd) {
		this.ip = ip;
		this.port = port;
		this.dbName = dbName;
		this.userName = userName;
		this.pwd = pwd;
	}
	
	// metodo get connection che viene chiamato nel try-with-resources garantisce che le connessioni vengano chiuse 
		//includendolo nel costruttore crea errori di connessione non chiusa correttamente
	private Connection getConnection() throws SQLException{
			
		return DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName
	               + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	               userName, pwd);
			 
	}
	
	public int salva(Utente utente) throws SQLException {
		
		String sql = "INSERT INTO Utenti(email,passwordHash) VALUES(?, ?)";
		int idGenerato = -1;
		
		 try (Connection conn = getConnection();
	          PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
			 
			 pstmt.setString(1, utente.getEmail());
	         pstmt.setString(2, utente.getPasswordHash());
	         
	         int affectedRows = pstmt.executeUpdate();

	         if (affectedRows == 0) {
	            throw new SQLException("Creazione utente fallita, nessuna riga modificata.");
	            }
	         try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	              if (generatedKeys.next()) {
	                  idGenerato = generatedKeys.getInt(1);
	              } else {
	                  throw new SQLException("Creazione utente fallita, nessun ID generato.");
	              }
	         }
		 }
		 return idGenerato;
			
	}
	
	public Utente findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Utenti WHERE email = ?";
        Utente u = null;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                	int id = rs.getInt("id");
                	String e = rs.getString("email");
                	String passwordHash = rs.getString("passwordHash");
                	Timestamp dataRegistrazione = rs.getTimestamp("dataRegistrazione");
                    u = new Utente(id,e,passwordHash,dataRegistrazione);
                }
            }
        }
        return u;
    }

	
}
