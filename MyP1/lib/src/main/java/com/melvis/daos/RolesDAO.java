package com.melvis.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.melvis.models.Roles;
import com.melvis.utils.P1ConnectionUtil;

public class RolesDAO implements RolesDAOInterface{
	
	@Override
	public Roles getRoleById(int id) {
	try (Connection conn = P1ConnectionUtil.getConnection()){
		String sql = "select * from ers_user_roles where ers_user_role_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery(); //execute the query into our new resultset
		while(rs.next()) {
			Roles role = new Roles(
					rs.getInt("ers_user_role_id"),
					rs.getString("user_role")
					);
			return role;
		}
		
	}catch(SQLException e)	{
		System.out.println("Get role failed");
		e.printStackTrace();
	}
	return null;
	}


	public boolean updateUserRole(String role, int id) {
		// TODO Auto-generated method stub
		try (Connection conn = P1ConnectionUtil.getConnection()){
			String sql = "update ers_user_roles set user_role = ? where ers_user_role_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, role);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("User role has been updated successfully");
			return true;
		}catch(SQLException e)	{
			System.out.println("Failed to update user role");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteRoleId(int id) {
		try (Connection conn = P1ConnectionUtil.getConnection()){
			String sql = "delete from ers_user_roles where ers_user_role_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println(id + " has been deleted successfully ");
			return true;
		}catch(SQLException e)	{
			System.out.println("Failed to delete");
			e.printStackTrace();
		}
		
		return false;
	}


	@Override
	public boolean insertRole(Roles role) {
		// TODO Auto-generated method stub
		try(Connection conn = P1ConnectionUtil.getConnection()){
			String sql = "insert into ers_user_roles(user_role) values (?);";
			//Instantiate a prepared statement to fill in the variable of our sql
			PreparedStatement ps = conn.prepareStatement(sql);
			//Fill in the values of our variables using ps.
			
			ps.setString(1, role.getRole());
			
			ps.executeUpdate();//this sends our sql off to the database
			
			System.out.println(role.getRole() + " was completed successfully...");
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Insert role failed");
			e.printStackTrace();
		return false;
		}
	}


	@Override
	public ArrayList<Roles> getRole() {
			 
			try(Connection conn = P1ConnectionUtil.getConnection()){
				String sql = "select * from ers_user_roles;";
				//no variables do we don't need a preparedStatement
				//What we use is
				Statement s = conn.createStatement();
				//import statement from java.sql
				//remember to execute 
				ResultSet rs = s.executeQuery(sql);
				
				ArrayList<Roles> roleList = new ArrayList<>();
				while(rs.next()) {
					Roles role = new Roles(
							rs.getInt("ers_user_role_id"),
							rs.getString("user_role")	
							);
					roleList.add(role);
				}
		return roleList;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Failed to get role please retry!!!");
		e.printStackTrace();
	}
			return null;
	}

	

}
