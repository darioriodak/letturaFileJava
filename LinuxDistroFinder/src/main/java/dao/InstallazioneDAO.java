package dao;

import java.sql.SQLException;

import entity.Installazione;

public interface InstallazioneDAO {
	
	 public int salva(Installazione installazione) throws SQLException;

}
