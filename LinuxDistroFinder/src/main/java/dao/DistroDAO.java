package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Distro;
import entity.ListaDistro;

public interface DistroDAO {
	
	public Distro caricaDistroById(int id) throws SQLException;
	public ListaDistro caricaTutteDistro() throws SQLException;
	public ListaDistro caricaDistroByName(String nome) throws SQLException;
	public int getNumeroDistro() throws SQLException;
//	public boolean deleteDistro(int id) throws SQLException;
//	public int insertDistro (Distro d) throws SQLException;
	
	
	

}
