package entity;

import org.json.JSONException;
import org.json.JSONObject;


public class InfoUtente {
	
	private String architetturaCpu;
	private int ramMB;
	private String cpuTier;
	private int spazioArchiviazione;
	private String tipoArchiviazione;
	private String produttoreGpu;
	private String modelloGpu;
	private String livelloEsperienza;
	private String useCase;
	private String ambienteDesktopPreferito;
	
	//costruttore con info essenziali
	public InfoUtente (String architetturaCpu,int ramMB,String cpuTier,String livelloEsperienza)  {
		this.architetturaCpu = architetturaCpu;
		this.ramMB = ramMB;
		this.cpuTier = cpuTier;
		this.livelloEsperienza = livelloEsperienza;
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

	public int getSpazioArchiviazione() {
		return spazioArchiviazione;
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

	public String getLivelloEsperienza() {
		return livelloEsperienza;
	}

	public void setLivelloEsperienza(String livelloEsperienza) {
		this.livelloEsperienza = livelloEsperienza;
	}

	public String getUseCase() {
		return useCase;
	}

	public void setUseCase(String useCase) {
		this.useCase = useCase;
	}

	public String getAmbienteDesktopPreferito() {
		return ambienteDesktopPreferito;
	}

	public void setAmbienteDesktopPreferito(String ambienteDesktopPreferito) {
		this.ambienteDesktopPreferito = ambienteDesktopPreferito;
	}
	
	public static InfoUtente fromJSON(String jsonString) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonString);

	    // 1. Verifica e leggi i parametri OBBLIGATORI
	    if (!jsonObject.has("architetturaCpu") || !jsonObject.has("cpuTier") ||
	        !jsonObject.has("ramMB") || !jsonObject.has("livelloEsperienza")) {
	        throw new JSONException("Parametri obbligatori mancanti: architetturaCpu, cpuTier, ramMB, livelloEsperienza");
	    }
	    String architetturaCpu = jsonObject.getString("architetturaCpu");
	    String cpuTier = jsonObject.getString("cpuTier"); 
	    int ramMB = jsonObject.getInt("ramMB");
	    String livelloEsperienza = jsonObject.getString("livelloEsperienza");

	   
	    InfoUtente info = new InfoUtente(architetturaCpu, ramMB, cpuTier, livelloEsperienza);

	    if (jsonObject.has("spazioArchiviazione")) {
	        info.setSpazioArchiviazione(jsonObject.getInt("spazioArchiviazione"));
	    }
	    if (jsonObject.has("tipoArchiviazione")) {
	        info.setTipoArchiviazione(jsonObject.getString("tipoArchiviazione"));
	    }
	    if (jsonObject.has("useCase")) {
	        info.setUseCase(jsonObject.getString("useCase"));
	    }
	    if (jsonObject.has("ambienteDesktopPreferito")) {
	        info.setAmbienteDesktopPreferito(jsonObject.getString("ambienteDesktopPreferito"));
	    }
	    if (jsonObject.has("produttoreGpu")) {
	        info.setProduttoreGpu(jsonObject.getString("produttoreGpu"));
	    }
	    if (jsonObject.has("modelloGpu")) {
	        info.setModelloGpu(jsonObject.getString("modelloGpu"));
	    }
	    return info;
	
	}
	
	
		
	
		
	

}
