package com.melvis.controllers;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.melvis.daos.StatusDAO;
import com.melvis.models.Status;

import io.javalin.http.Handler;

public class StatusController {
StatusDAO sDAO = new StatusDAO();
	
	public Handler getStatusHandler = (ctx) -> {
		ArrayList<Status> stat = sDAO.getStatus();
		
		Gson gson = new Gson();
		String JSONstat = gson.toJson(stat);
		ctx.result(JSONstat);
		ctx.status(200);
	};
	
	public Handler updateStatusHandler = (ctx) ->{
		
		String stats= ctx.body();
		int id = Integer.parseInt(ctx.pathParam("reim_status_id")); //path param gives us the value the user sends in
		//parseInt converts ctx.body to integer
		
		if(sDAO.updateUserStatus(stats, id)) {
			ctx.status(202);
			
		}else {
			ctx.status(406);
		}
	};
	
	public Handler deleteStatusHandler = (ctx) ->{
		// A string to hold the role title which comes in to hold the path parameter
		int id = Integer.parseInt(ctx.pathParam("reim_status_id")); //to delete from the db
		
		if(sDAO.deleteStatusId(id)) {
			ctx.status(202);
			
		}else {
			ctx.status(406);
		}
	};
	
	public Handler insertStatusHandler = (ctx) -> {
		//Post data is being posted to your backend which we access with ctx.body();
		//body?? it refers to the body of the http request which were the incoming data is found
		String body = ctx.body();
		//create a new Gson object to make Json java conversion
		Gson gson = new Gson();
		
		Status newStat = gson.fromJson(body, Status.class);
		String userNameJSON = gson.toJson(newStat);
		
		
		if(sDAO.insertStatus(newStat)) {
			ctx.result(userNameJSON);
			ctx.status(202); //202 stands for acception
		
		}else {ctx.status(406); //406 means wasn't accepted
		}
		
	
	};
	

}
