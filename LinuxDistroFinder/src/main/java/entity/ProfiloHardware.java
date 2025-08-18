package entity;

import java.sql.Timestamp;

public class ProfiloHardware {
	
	//parametri aggiuntivi
	private int id;
	private Integer idUtente; // può essere null
	private Timestamp dataInserimento;
	
	private String architetturaCpu;
	private int ramMB;
	private String cpuTier;
	private int spazioArchiviazione;
	private String tipoArchiviazione;
	private String produttoreGpu;
	private String modelloGpu;
	private String livelloEsperienza;
	private String ambienteDesktopPreferito;


	//per costruire il profilo da inserire nel database id e data vengono gestiti dal db , id Utente settato successivamente quando si ha la sessione dell utente 
	
	public ProfiloHardware (InfoUtente infoutente) {
		
		this.architetturaCpu = infoutente.getArchitetturaCpu(); 
	    this.ramMB = infoutente.getRamMB();
	    this.cpuTier = infoutente.getCpuTier();
	    this.livelloEsperienza = infoutente.getLivelloEsperienza();
	    this.spazioArchiviazione = infoutente.getSpazioArchiviazione();
	    this.tipoArchiviazione = infoutente.getTipoArchiviazione();
	    this.ambienteDesktopPreferito = infoutente.getAmbienteDesktopPreferito();
	    this.produttoreGpu = infoutente.getProduttoreGpu();
	    this.modelloGpu = infoutente.getModelloGpu();
	}
	
	//per ricostruire il profilo dal database
	
	 public ProfiloHardware(int id, Integer idUtente, String architetturaCpu, int ramMB, String cpuTier, String livelloEsperienza,
			 int spazioArchiviazione,String tipoArchiviazione,
             String ambienteDesktopPreferito, String produttoreGpu, String modelloGpu, Timestamp dataInserimento) {
		 this.id = id;
		 if(idUtente != null) {
			 this.idUtente = idUtente;
			 
		 }
		 this.architetturaCpu = architetturaCpu;
		 this.ramMB = ramMB;
		 this.cpuTier = cpuTier;
		 this.livelloEsperienza = livelloEsperienza;
		 this.spazioArchiviazione = spazioArchiviazione;
		 this.tipoArchiviazione = tipoArchiviazione;
		 this.ambienteDesktopPreferito = ambienteDesktopPreferito;
		 this.produttoreGpu = produttoreGpu;
		 this.modelloGpu = modelloGpu;
		 this.dataInserimento = dataInserimento;
	 }
	 
	 // getter e setter
	 
	 public Integer getIdUtente() {
	        return idUtente;
	    }

	    public void setIdUtente(Integer idUtente) {
	        this.idUtente = idUtente;
	    }

	    public Timestamp getDataInserimento() {
	        return this.dataInserimento;
	    }

	    public void setDataInserimento(Timestamp dataInserimento) {
	        this.dataInserimento = dataInserimento;
	    }

	    public String getArchitetturaCpu() {
	        return architetturaCpu;
	    }

	    public void setArchitetturaCpu(String architetturaCpu) {
	        this.architetturaCpu = architetturaCpu;
	    }

	    public int getRamMB() {
	        return ramMB;
	    }

	    public void setRamMB(int ramMB) {
	        this.ramMB = ramMB;
	    }

	    public String getCpuTier() {
	        return cpuTier;
	    }

	    public void setCpuTier(String cpuTier) {
	        this.cpuTier = cpuTier;
	    }

	    public String getLivelloEsperienza() {
	        return livelloEsperienza;
	    }

	    public void setLivelloEsperienza(String livelloEsperienza) {
	        this.livelloEsperienza = livelloEsperienza;
	    }
	    
	    public int getSpazioArchiviazione() {
	    	return this.spazioArchiviazione;
	    }
	    
	    public void setSpazioArchiviazione(int spazioArchiviazione) {
	    	this.spazioArchiviazione = spazioArchiviazione;
	    }

	    public String getTipoArchiviazione() {
	        return tipoArchiviazione;
	    }

	    public void setTipoArchiviazione(String tipoArchiviazione) {
	        this.tipoArchiviazione = tipoArchiviazione;
	    }

	    public String getAmbienteDesktopPreferito() {
	        return ambienteDesktopPreferito;
	    }

	    public void setAmbienteDesktopPreferito(String ambienteDesktopPreferito) {
	        this.ambienteDesktopPreferito = ambienteDesktopPreferito;
	    }

	    public String getProduttoreGpu() {
	        return produttoreGpu;
	    }

	    public void setProduttoreGpu(String produttoreGpu) {
	        this.produttoreGpu = produttoreGpu;
	    }

	    public String getModelloGpu() {
	        return modelloGpu;
	    }

	    public void setModelloGpu(String modelloGpu) {
	        this.modelloGpu = modelloGpu;
	    }
	    
	  

	    @Override
	    public String toString() {
	        return "ProfiloHardware{" +
	                "idUtente=" + idUtente +
	                ", dataInserimento=" + dataInserimento +
	                ", architetturaCpu='" + architetturaCpu + '\'' +
	                ", ramMB=" + ramMB +
	                ", cpuTier='" + cpuTier + '\'' +
	                ", livelloEsperienza='" + livelloEsperienza + '\'' +
	                ", spazioArchiviazione='" + spazioArchiviazione + '\'' +
	                ", tipoArchiviazione='" + tipoArchiviazione + '\'' +
	                ", ambienteDesktopPreferito='" + ambienteDesktopPreferito + '\'' +
	                ", produttoreGpu='" + produttoreGpu + '\'' +
	                ", modelloGpu='" + modelloGpu +
	                '}';
	    }
    
	
}
