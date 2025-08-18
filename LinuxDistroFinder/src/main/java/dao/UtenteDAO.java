package dao;

import java.sql.SQLException;

import entity.Utente;

public interface UtenteDAO {
	
	public int salva(Utente utente) throws SQLException;
	public Utente findByEmail(String email) throws SQLException;

}
