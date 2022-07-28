package com.melvis;
import java.sql.Connection;
import java.sql.SQLException;

import com.melvis.controllers.ReimbursementController;
import com.melvis.controllers.RolesController;
import com.melvis.controllers.StatusController;
import com.melvis.controllers.TypeController;
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
		
		//Login
		AuthUserDAO aDAO = new AuthUserDAO();
		System.out.println(aDAO.register("MelvisAche", "Explosive01", "Melvis", "Ache", "achemelvis@gmail.com") || aDAO.register("Khalifa", "Khalifa02", "Denning", "Billah", "khalifa01@gmail.com"));
		UserAuthController ac = new UserAuthController();
		app.post("/login", ac.loginHandler);
		app.post("/register", ac.registerHandler);

	
	//Reimbursement Handler	
		ReimbursementController pc = new ReimbursementController();
		app.get("/reimbursement", pc.getReimbursementHandler);
		app.post("/insertReimbursement", pc.insertReimbursementHandler);
		app.put("/ers_reimbursement/:reimb_id", pc.updateReimbursementHandler);
		app.delete("/ers_reimbursement/:reimb_id", pc.deleteReimbursementHandler);
		
		//Roles Handler
		RolesController rc = new RolesController();
		app.get("/role", rc.getRoleHandler);
		app.post("/insertRole", rc.insertRoleHandler);
		app.put("/ers_user_roles/:ers_user_role_id", rc.updateRoleHandler);
		app.delete("/ers_user_roles/:ers_user_role_id", rc.deleteRoleHandler);
		
		//Status Handler
		StatusController sc = new StatusController();
		app.get("/status", sc.getStatusHandler);
		app.post("/insertStatus", sc.insertStatusHandler);
		app.put("/ers_reimbursement_status/:reim_status_id", sc.updateStatusHandler);
		app.delete("/ers_reimbursement_status/:reim_status_id", sc.deleteStatusHandler);
		
		//Type Handler
		TypeController tc = new TypeController();
		app.get("/type", tc.getTypeHandler);
		app.post("/insertType", tc.insertTypeHandler);
		app.put("/ers_reimbursement_type/:reimb_type_id", tc.updateTypeHandler);
		app.delete("/ers_reimbursement_type/:reimb_type_id", tc.deleteTypeHandler);
		
		
}
}
