package entity;

import org.json.JSONObject;

public class Distro {
	
	private int id;
	private String nome;
	private int annoFondazione;
	private int minRamMB;
	private int numeroPacchetti;
	private boolean beginnerFriendly;
	private String gestorePacchetti;
	private String guidaPdf;

	public Distro(int id,String nome, int annoFondazione, int minRamMB, int numeroPacchetti, boolean beginnerFriendly, String gestorePacchetti,String guidaPdf) {
		this.id = id;
		this.nome = nome; 
		this.annoFondazione = annoFondazione ;
		this.minRamMB = minRamMB ;
		this.numeroPacchetti = numeroPacchetti ;
		this.beginnerFriendly = beginnerFriendly ; 
		this.gestorePacchetti = gestorePacchetti ;
		this.guidaPdf = guidaPdf;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public int getMinRamMB() {
		return this.minRamMB;
	}
	public void setminRamMB(int minRamMB) {
		this.minRamMB = minRamMB ;
	}
	
	public boolean getBeginnerFriendly() {
		return this.beginnerFriendly;
	}
	
	
	public String getGestorePacchetti() {
		return this.gestorePacchetti;
	}
	
	public void setGestorePacchetti(String gestorePacchetti) {
		this.gestorePacchetti = gestorePacchetti;
	}
	
	public int getAnnoFondazione() {
		return this.annoFondazione;
	}
	public void setAnnoFondazione(int annoFondazione) {
		this.annoFondazione = annoFondazione;
	}
	public int getNumeroPacchetti() {
		return this.numeroPacchetti;
	}
	public void setNumeroPacchetti(int numeroPacchetti) {
		this.numeroPacchetti=numeroPacchetti;
	}
	public String getGuidaPdf() {
		return this.guidaPdf;
	}
	public void setGuidaPdf(String guidaPdf) {
		this.guidaPdf = guidaPdf;
	}
	
	public String toString() {
		return "Distribuzione : " + this.nome + " anno fondazione : " + this.annoFondazione + " ram minima : " + this.minRamMB + " numero pacchetti : " + this.numeroPacchetti + " beginner friendly : " + this.beginnerFriendly + " gestore Pacchetti : " + this.gestorePacchetti;
	}
	
	public String toJSONString() {
		return new JSONObject(this).toString();
	}
	
	
	
}
