package com.melvis.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.melvis.daos.RolesDAO;
import com.melvis.models.Roles;

import io.javalin.http.Handler;

public class RolesController {
RolesDAO rDAO = new RolesDAO();
	
	public Handler getRoleHandler = (ctx) -> {
		ArrayList<Roles> role = rDAO.getRole();
		
		Gson gson = new Gson();
		String JSONrole = gson.toJson(role);
		ctx.result(JSONrole);
		ctx.status(200);
	};
	
	public Handler updateRoleHandler = (ctx) ->{
		
		String role= ctx.body();
		int id = Integer.parseInt(ctx.pathParam("ers_user_role_id")); //path param gives us the value the user sends in
		//parseInt converts ctx.body to integer
		
		if(rDAO.updateUserRole(role, id)) {
			ctx.status(202);
			
		}else {
			ctx.status(406);
		}
	};
	
	public Handler deleteRoleHandler = (ctx) ->{
		// A string to hold the role title which comes in to hold the path parameter
		int id = Integer.parseInt(ctx.pathParam("ers_user_role_id")); //to delete from the db
		
		if(rDAO.deleteRoleId(id)) {
			ctx.status(202);
			
		}else {
			ctx.status(406);
		}
	};
	
	public Handler insertRoleHandler = (ctx) -> {
		//Post data is being posted to your backend which we access with ctx.body();
		//body?? it refers to the boody of the http request which were the incoming data is found
		String body = ctx.body();
		//create a new Gson object to make Json java conversion
		Gson gson = new Gson();
		//turn the incoming Json string directly into an Role object
		Roles newRole = gson.fromJson(body, Roles.class);
		
		if(rDAO.insertRole(newRole)) {
			ctx.status(202); //202 stands for acception
		
		}else {ctx.status(406); //406 means wasn't accepted
		}
		
	
	};
	

}
