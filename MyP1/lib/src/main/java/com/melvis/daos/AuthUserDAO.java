package com.melvis.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.melvis.utils.P1ConnectionUtil;

public class AuthUserDAO {

	public boolean register(String username, String password, String firstName, String lastName, String email) {
		// TODO Auto-generated method stub
		
		try(Connection conn = P1ConnectionUtil.getConnection()){
			String sql = "select * from ers_users where ers_username = ? and ers_password = ? and user_first_name = ? and user_last_name = ?and user_email = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, email);
			
			
			ResultSet rs = ps.executeQuery();
			//if anything gets returned then user exist
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SignUp failed... Please try again.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		
		try(Connection conn = P1ConnectionUtil.getConnection()){
			String sql = "select * from ers_users where ers_username = ? and ers_password = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			
			ResultSet rs = ps.executeQuery();
			//if anything gets returned then user exist
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Login failed... Please try logging in again.");
			e.printStackTrace();
		}
		return false;
	}

}
