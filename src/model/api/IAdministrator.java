package model.api;

import model.impl.Administrator;

public interface IAdministrator {
	Administrator setLastname(String lastname);
	Administrator setFirstname(String firstname);
	Administrator setLogin(String login);
	Administrator setMdp(String mdp);
	int getId();
	String getLastname();
	String getFirstname();
	String getLogin();
	String getMdp();
}
