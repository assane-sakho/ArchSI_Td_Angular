package model.impl;

public class Administrator {
	
    private final int id;
    private String lastname;
    private String firstname;
    private String login;
    private String mdp;

    public Administrator(int id, String lastname, String firstname, String login, String mdp) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.login = login;
        this.mdp = mdp;
    }

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }
    
    public String getLogin() {
    	return login;
    }
    
    public String getMdp() {
    	return mdp;
    }
    
    public Administrator setLastname(String lastname) {
    	this.lastname = lastname;
    	return this;
    }
    
    public Administrator setFirstname(String firstname) {
    	this.firstname = firstname;
    	return this;
    }
    
    public Administrator setLogin(String login) {
    	this.login = login;
    	return this;
    }
    
    public Administrator setMdp(String mdp) {
    	this.mdp = mdp;
    	return this;
    }
    
    
    
}
