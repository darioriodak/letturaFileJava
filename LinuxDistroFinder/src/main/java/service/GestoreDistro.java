package service;

import java.sql.SQLException;

import entity.InfoUtente;
import entity.ListaDistro;
import entity.ProfiloHardware;

public interface GestoreDistro {
	
	public ListaDistro recuperaDistro() throws SQLException;
	public ListaDistro consigliaDistro(ProfiloHardware profiloHardware) throws SQLException;
	

}
