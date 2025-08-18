package service;

import java.sql.SQLException;

import dao.DistroDAO;
import entity.InfoUtente;
import entity.ListaDistro;
import entity.ProfiloHardware;

public class GestoreDistroImpl implements GestoreDistro{
	
	DistroDAO d;
	
	
	public GestoreDistroImpl(DistroDAO d) {
		
		this.d = d;
		
	}
	
	public ListaDistro recuperaDistro() throws SQLException {
		
		return d.caricaTutteDistro();	
	}
	
	// algoritmo di raccomandazione distro principale, da implementare
	public ListaDistro consigliaDistro(ProfiloHardware profiloHardware) throws SQLException {
		ListaDistro listaConsigliata = new ListaDistro();
		listaConsigliata = d.caricaTutteDistro();
		return listaConsigliata;
		
	}

}
