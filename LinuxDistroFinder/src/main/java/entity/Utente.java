package entity;

import java.sql.Timestamp;

public class Utente {
	private int id;
    private String email;
    private String passwordHash; 
    private Timestamp dataRegistrazione;

    

    public Utente(int id , String email , String passwordHash, Timestamp dataRegistrazione) {
    	this.id = id;
    	this.email = email;
    	this.passwordHash = passwordHash;
    	this.dataRegistrazione = dataRegistrazione;
    }
    
    public Utente(String email,String passwordHash) {
    	this.email = email;
    	this.passwordHash = passwordHash;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public Timestamp getDataRegistrazione() {
        return dataRegistrazione;
    }
    public void setDataRegistrazione(Timestamp dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }

}
