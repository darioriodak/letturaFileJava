package entity;

public class Installazione {
	
	private int idDistro;
	private int idProfiloHardware;
	private String useCase;
	
	public Installazione(int idProfilo, int idDistro, String useCase) {
        this.idProfiloHardware = idProfilo;
        this.idDistro = idDistro;
        this.useCase = useCase;
    }

    // Getter e Setter
    public int getIdProfiloHardware() {
        return idProfiloHardware;
    }

    public void setIdProfiloHardware(int idProfilo) {
        this.idProfiloHardware = idProfilo;
    }

    public int getIdDistro() {
        return idDistro;
    }

    public void setIdDistro(int idDistro) {
        this.idDistro = idDistro;
    }

    public String getUsecase() {
        return useCase;
    }

    public void setUsecase(String useCase) {
        this.useCase = useCase;
    }

    @Override
    public String toString() {
        return "Installazione{" +
                "idProfiloHardware=" + idProfiloHardware +
                ", idDistro=" + idDistro +
                ", usecase='" + useCase + '\'' +
                '}';
    }

}
