package com.melvis.services;

import com.melvis.daos.AuthUserDAO;

public class AuthUsersServices {
	
	AuthUserDAO aDAO = new AuthUserDAO();
	
	public String register(String username, String Password, String firstName, String lastName, String email) {
		if (aDAO.register(username, Password, firstName, lastName, email)) {
			return username;
		}
		return null;
	}
	
	public String login(String username, String Password) {
		if (aDAO.login(username, Password)) {
			return username;
		}
		return null;
	}

}
