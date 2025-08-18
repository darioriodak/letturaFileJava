package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Distro;
import entity.InfoUtente;
import entity.ListaDistro;
import entity.ProfiloHardware;
import entity.Utente;
import security.BCrypt;
import service.GestoreDistroImpl;

public class prova {

	public static void main(String[] args) throws SQLException {
		
		/*UtenteDAO u = new UtenteDAOImpl("fedora","3306","distro_prova","dario","Telemarketing56-");
		
		String password = "telemarket";
		String saltGenerato = BCrypt.gensalt();
		String passwordHash = BCrypt.hashpw(password, saltGenerato);
		
		Utente prova = new Utente("dario.nadal@hotmail.it",passwordHash);
		
		int res = u.salva(prova);
		
		System.out.println(res);*/
				
	
		
		
		
	
		
		
		
		/*
		try {
			lista = d.caricaTutteDistro();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*for(Distro di : lista) {
			System.out.println(di);
		}*/
		
		
		
		//ListaDistro l = d.caricaTutteDistro();
		//for (Distro di : l ) {
			//	System.out.println(di.toJSONString());
			//}
		/*UtenteDAO utenteDAO = new UtenteDAOImpl("fedora","3306","distro_prova","dario","Telemarketing56-");
		
		 
		Utente u = new Utente(utenteCompleto);
		try {
			utenteDAO.salva(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(u);*/
		
		
		
		
	}
}
