package com.melvis.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;

import com.melvis.daos.TypeDAO;

import com.melvis.models.Type;

import io.javalin.http.Handler;

public class TypeController {
TypeDAO tDAO = new TypeDAO();
	
	public Handler getTypeHandler = (ctx) -> {
		ArrayList<Type> type = tDAO.getType();
		
		Gson gson = new Gson();
		String JSONtype = gson.toJson(type);
		ctx.result(JSONtype);
		ctx.status(200);
	};
	
	public Handler updateTypeHandler = (ctx) ->{
		
		String type= ctx.body();
		int id = Integer.parseInt(ctx.pathParam("reim_type_id")); //path param gives us the value the user sends in
		//parseInt converts ctx.body to integer
		
		if(tDAO.updateReimbType(type, id)) {
			ctx.status(202);
			
		}else {
			ctx.status(406);
		}
	};
	
	public Handler deleteTypeHandler = (ctx) ->{
		// A string to hold the role title which comes in to hold the path parameter
		int id = Integer.parseInt(ctx.pathParam("reim_type_id")); //to delete from the db
		
		if(tDAO.deleteTypeId(id)) {
			ctx.status(202);
			
		}else {
			ctx.status(406);
		}
	};
	
	public Handler insertTypeHandler = (ctx) -> {
		//Post data is being posted to your backend which we access with ctx.body();
		//body?? it refers to the boody of the http request which were the incoming data is found
		String body = ctx.body();
		//create a new Gson object to make Json java conversion
		Gson gson = new Gson();
		//turn the incoming Json string directly into an Role object
		Type newType = gson.fromJson(body, Type.class);
		
		if(tDAO.insertType(newType)) {
			ctx.status(202); //202 stands for acception
		
		}else {ctx.status(406); //406 means wasn't accepted
		}
		
	
	};
	

}
