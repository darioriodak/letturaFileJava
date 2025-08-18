package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.JSONArray;
import entity.Distro;
import entity.ListaDistro;



public class DistroDAOImpl implements DistroDAO {
	
	private String ip;
	private String port;
	private String dbName;
	private String userName;
	private String pwd;
	
	public DistroDAOImpl(String ip, String port, String dbName, String userName, String pwd) {
		
		this.ip = ip;
		this.port = port;
		this.dbName = dbName;
		this.userName = userName;
		this.pwd = pwd;
		
	}
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName
		             + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		             userName, pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		 
		 
	 }
	
	public Distro caricaDistroById(int id) throws SQLException {
		
		String query = "SELECT * FROM Distribuzioni WHERE id = \"" + id + "\"";
		Distro distro = null;
		
		try (Connection conn = getConnection();
		     PreparedStatement pstmt = conn.prepareStatement(query)){
			
			
			try (ResultSet rs = pstmt.executeQuery()) {
	            
	            if (rs.next()) {
	                
	                int i = rs.getInt("id");
	                String nome = rs.getString("nome");
	                int anno = rs.getInt("annoFondazione");
	                int ram = rs.getInt("minram");
	                int  np = rs.getInt("numeroPacchetti");
	                boolean bf = rs.getBoolean("beginnerFriendly");
	                String gp = rs.getString("gestorePacchetti");
	                String gu = rs.getString("guidaPdf");

	                distro = new Distro(i, nome, anno, ram,np , bf, gp,gu);
	                
	            }
	            return distro ;
	        } 
			
	    } 
		
		
		
	}
	
	
	public ListaDistro caricaTutteDistro() throws SQLException{
		String query = "SELECT * FROM Distribuzioni ";
		
		ListaDistro res = new ListaDistro();
		
		
		try (Connection conn = getConnection();
			     PreparedStatement pstmt = conn.prepareStatement(query)){
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					String nome = rset.getString(2);
					int i = rset.getInt(1);
					int anno = rset.getInt(3);
					int ram = rset.getInt(4);
					int np = rset.getInt(5);
					boolean bf = rset.getBoolean(6);
					String gp = rset.getString(7);
					String gu = rset.getString(8);

					Distro d = new Distro(i,nome,anno,ram,np,bf,gp,gu);
					
					res.add(d);
					
				}
				
			}
			return res;
		}
									
	}
			

	
	public ListaDistro caricaDistroByName(String nome) throws SQLException {
		
		String query = "SELECT* FROM Distribuzioni WHERE nome = \"" + nome + "\"";
		ListaDistro res = new ListaDistro();

		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)){
			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					String name = rset.getString(2);
					int id = rset.getInt(1);
					int anno = rset.getInt(3);
					int ram = rset.getInt(4);
					int np = rset.getInt(5);
					boolean bf = rset.getBoolean(6);
					String gp = rset.getString(7);
					String gu = rset.getString(8);

					Distro d = new Distro(id,name,anno,ram,np,bf,gp,gu);
					
					res.add(d);
				}
				return res;
				
				
			}
			
		}
		
		
					
	}
	
	public int getNumeroDistro() throws SQLException {
		
		String query = "SELECT COUNT(*) FROM Distribuzioni ";
		int res = -1;
		
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)){
			try (ResultSet rset = pstmt.executeQuery()) {
				
				if (rset.next())
					res = rset.getInt(1);
				return res;	
		
			}
			
		}
			
		
	}
	
	
	/* sistemare anche questi
	 * 
	 * 
	public boolean deleteDistro(int id) {

		String query = "DELETE FROM Distribuzioni WHERE id='" + id + "'";
		
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query)){
			try (ResultSet rset = pstmt.executeQuery()) {
				
				
			}
			
		}
				

		
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
		
		
	public int insertDistro(Distro d) {
		String SQL = "INSERT INTO Distribuzioni(nome,annoFondazione,minram,numeroPacchetti,beginnerFriendly,usecase) " + "VALUES(?,?,?,?,?,?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setString(2, d.getNome());
			pstmt.setInt(3,d.getAnnoFondazione());
			pstmt.setInt(4,d.getMinRamMB());
			pstmt.setInt(5,d.getNumeroPacchetti());
			pstmt.setBoolean(6,d.getBeginnerFriendly());
			pstmt.setString(7,d.getGestorePacchetti());

			int affectedRows = pstmt.executeUpdate();

			return affectedRows;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	*/
		
	

}
