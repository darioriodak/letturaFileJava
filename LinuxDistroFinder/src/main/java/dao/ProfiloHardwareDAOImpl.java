package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;



import entity.ProfiloHardware;

public class ProfiloHardwareDAOImpl implements ProfiloHardwareDAO{
	
	String ip;
	String port;
	String dbName;
	String userName;
	String pwd;
	
	public ProfiloHardwareDAOImpl(String ip,String port, String dbName, String userName, String pwd) {
		
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
	
	// inserisce il profiloHardware e ritorna l'id generato automaticamente 
	public int salva(ProfiloHardware profiloHardware) throws SQLException {
		
		String sql = "INSERT INTO ProfiliHardware (idUtente,architetturaCpu, ramMB, cpuTier, livelloEsperienza,spazioArchiviazione,tipoArchiviazione, ambienteDesktopPreferito, produttoreGpu, modelloGpu) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
   
		int generatedId = -1;

  
		try (Connection conn = getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, profiloHardware.getIdUtente()); 
			pstmt.setString(2, profiloHardware.getArchitetturaCpu());
			pstmt.setInt(3, profiloHardware.getRamMB());
			pstmt.setString(4,profiloHardware.getCpuTier());
			pstmt.setString(5,profiloHardware.getLivelloEsperienza());
			pstmt.setInt(6, profiloHardware.getSpazioArchiviazione());
			
			//nessun controllo i tipi stringa null vengono automaticamente propagati nel db 
			pstmt.setString(7,profiloHardware.getTipoArchiviazione());
			pstmt.setString(8,profiloHardware.getAmbienteDesktopPreferito());
			pstmt.setString(9,profiloHardware.getProduttoreGpu());
			pstmt.setString(10,profiloHardware.getModelloGpu());
	
	        int affectedRows = pstmt.executeUpdate();
	        
	        if (affectedRows == 0 ) {
	        	throw new SQLException();
	        }
	
	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                generatedId = generatedKeys.getInt(1);
	            } else {
	                throw new SQLException("Creazione utente fallita, nessun ID generato.");
	            }
	        }
	        return generatedId;
	    } 
	
		
	}
}



