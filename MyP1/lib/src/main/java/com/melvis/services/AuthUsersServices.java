package com.melvis.services;

import com.melvis.daos.AuthUserDAO;

public class AuthUsersServices {
	
	AuthUserDAO aDAO = new AuthUserDAO();	
	public String login(String username, String Password, String firstName, String lastName, String email) {
		if (aDAO.login(username, Password, firstName, lastName, email)) {
			return username;
		}
		return null;
	}

}
