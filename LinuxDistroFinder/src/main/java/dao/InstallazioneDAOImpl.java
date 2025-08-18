package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.Installazione;

public class InstallazioneDAOImpl implements InstallazioneDAO {
	
	String ip;
	String port;
	String dbName;
	String userName;
	String pwd;
	
	
	public InstallazioneDAOImpl(String ip, String port, String dbName, String userName, String pwd) {
		
		this.ip = ip;
		this.port = port;
		this.dbName = dbName;
		this.userName = userName;
		this.pwd = pwd;
		
	}
	
	private Connection getConnection() throws SQLException{
		
		return DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                userName, pwd);	
	}

	@Override
	public int salva(Installazione i) throws SQLException {
	
		String SQL = "INSERT INTO Installazione(profiloHardware,distro,useCase)" + "VALUES(?,?,?)";
		int res = -1;
		
		if (i == null) {
            throw new IllegalArgumentException("L'oggetto Installazione non può essere null.");
        }

        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, i.getIdProfiloHardware());
            pstmt.setInt(2, i.getIdDistro());
            pstmt.setString(3, i.getUsecase());

            int affectedRows = pstmt.executeUpdate();

            
            if (affectedRows == 0) {
                throw new SQLException("Creazione del record di installazione fallita, nessuna riga modificata.");
            }else {
            	res = affectedRows;
            }
            return res;
            

        } 
		
	}

}
