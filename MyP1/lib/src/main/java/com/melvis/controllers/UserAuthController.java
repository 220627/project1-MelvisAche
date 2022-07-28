package com.melvis.controllers;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import com.melvis.models.LoginUsersDTO;
import com.melvis.services.AuthUsersServices;

import io.javalin.http.Handler;

public class UserAuthController {
	AuthUsersServices as = new AuthUsersServices();
	
	public static HttpSession ses;
	
	public Handler registerHandler= (ctx)->{
		String body = ctx.body();
		Gson gson = new Gson();
		LoginUsersDTO ldto = gson.fromJson(body, LoginUsersDTO.class);
		String signUpUsername = as.register(ldto.getUsername(), ldto.getPassword(), ldto.getFirstName(), ldto.getLastName(), ldto.getEmail());
		String userNameJSON = gson.toJson(signUpUsername);
		if(signUpUsername != null){
		
			ses = ctx.req.getSession();
			ctx.result(userNameJSON);
			ctx.status(202);
			
		}else {ctx.status(401); //unauthorized
		       //ctx.result("You haven't logged in");
		}
		
		
	};
	
	public Handler loginHandler= (ctx)->{
		String body = ctx.body();
		Gson gson = new Gson();
		LoginUsersDTO ldto = gson.fromJson(body, LoginUsersDTO.class);
		String loginUsername = as.login(ldto.getUsername(), ldto.getPassword());
		String userNameJSON = gson.toJson(loginUsername);
		if(loginUsername != null){
		
			ses = ctx.req.getSession();
			ctx.result(userNameJSON);
			ctx.status(202);
			
		}else {ctx.status(401); //unauthorized
		       //ctx.result("You haven't logged in");
		}
		
		
	};


}
