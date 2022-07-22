package com.melvis.daos;

import org.eclipse.jetty.server.Authentication.User;

public interface AuthUserDAOInterface {
	boolean insertUser(User user);

}
