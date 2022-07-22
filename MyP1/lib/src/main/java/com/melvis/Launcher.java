package com.melvis;
import java.sql.Connection;
import java.sql.SQLException;
import com.melvis.controllers.UserAuthController;
import com.melvis.daos.AuthUserDAO;
import com.melvis.utils.P1ConnectionUtil;
import io.javalin.Javalin;

public class Launcher {
	public static void main(String[] args) {
		
	
	System.out.println("============ Welcome to the Employee Reimbursement Management System=====");
	try(Connection conn = P1ConnectionUtil.getConnection()){
		System.out.println("Connection Successful...");
	}catch(SQLException e) {
		System.out.println("Connection failed...");
		e.printStackTrace();
	}
		
		Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins();
				}
				).start(8181); 
		
		AuthUserDAO aDAO = new AuthUserDAO();
		System.out.println(aDAO.login("MelvisAche", "Explosive01", "Melvis", "Ache", "achemelvis@gmail.com") || aDAO.login("Khalifa", "Khalifa02", "Denning", "Billah", "khalifa01@gmail.com"));
		UserAuthController ac = new UserAuthController();
		app.post("/login", ac.loginHandler);

	

	
	//Login
		
}
}
