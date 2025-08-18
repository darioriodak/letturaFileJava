package dao;

import java.sql.SQLException;

import entity.ProfiloHardware;

public interface ProfiloHardwareDAO {
	
	//ritorna l'id dell utente salvato 
	int salva(ProfiloHardware utente) throws SQLException;
	

	

}
