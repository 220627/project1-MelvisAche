package com.melvis.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.melvis.daos.ReimbursementDAO;
import com.melvis.models.Reimbursement;

import io.javalin.http.Handler;

public class ReimbursementController {
	
	ReimbursementDAO rDAO = new ReimbursementDAO();

	public Handler getReimbursementHandler = (ctx) -> {
		ArrayList<Reimbursement> reimb = rDAO.getReimbursement();
		
		Gson gson = new Gson();
		String JSONreimbursement = gson.toJson(reimb);
		ctx.result(JSONreimbursement);
		ctx.status(200);
	};

	public Handler insertReimbursementHandler = (ctx) -> {
		//Post data is being posted to your backend which we access with ctx.body();
		//body?? it refers to the boody of the http request which were the incoming data is found
		String body = ctx.body();
		//create a new Gson object to make Json java conversion
		Gson gson = new Gson();
		//turn the incoming Json string directly into an Reimbursement object
		Reimbursement newReimb = gson.fromJson(body, Reimbursement.class);
		
		//If it succeeds, it'll call return true since that's the return type of insertReimbursement
		if(rDAO.insertReimburse(newReimb)) {
			ctx.status(202); //202 stands for acception
		
		}else {ctx.status(406); //406 means wasn't accepted
		}
		
	
	};
	
	public Handler deleteReimbursementHandler = (ctx) ->{
		
		int id = Integer.parseInt(ctx.pathParam("reimb_id")); //to delete from the db
		
		if(rDAO.deleteReimbursementId(id)) {
			ctx.status(202);
			
		}else {
			ctx.status(406);
		}
	};
	
	public Handler updateReimbursementHandler = (ctx) ->{
		
		Double amount = Double.parseDouble( ctx.body());  //parseInt converts ctx.body to integer
		int id = Integer.parseInt(ctx.pathParam("reimb_id"));//path param gives us the value the user sends in
		
		if(rDAO.updateReimbursementAmount(amount, id)) {
			ctx.status(202);
			
		}else {
			ctx.status(406);
		}
	};
	
	
}
