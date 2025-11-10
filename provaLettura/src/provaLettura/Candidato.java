package provaLettura;

public class Candidato {
	
	public String codCandidatura;
	private int voto;
	
	public Candidato(String codCandidatura, int voto) {
		this.codCandidatura = codCandidatura;
		this.voto = voto;
	}
	
	public String toString() {
		return "Candidato: " + this.codCandidatura + " " + this.voto ;
	}
	
	public int getVoto() {
		return this.voto;
	}
	

}
