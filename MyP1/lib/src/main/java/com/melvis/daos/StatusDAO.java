package com.melvis.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.melvis.models.Status;
import com.melvis.utils.P1ConnectionUtil;

public class StatusDAO implements StatusDAOInterface{

//	public Status getStatusById(int statusFK) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public Status getStatusById(int id) {
	try (Connection conn = P1ConnectionUtil.getConnection()){
		String sql = "select * from ers_reimbursement_status where reim_status_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery(); //execute the query into our new resultset
		while(rs.next()) {
			Status stats = new Status(
					rs.getInt("reim_status_id"),
					rs.getString("reimb_status")
					);
			return stats;
		}
		
	}catch(SQLException e)	{
		System.out.println("Get status failed");
		e.printStackTrace();
	}
	return null;
	}


	public boolean updateUserStatus(String status, int id) {
		// TODO Auto-generated method stub
		try (Connection conn = P1ConnectionUtil.getConnection()){
			String sql = "update ers_reimbursement_status set reimb_status = ? where reim_status_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Reimbursement status has been updated successfully");
			return true;
		}catch(SQLException e)	{
			System.out.println("Failed to update reimbursement status");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteStatusId(int id) {
		try (Connection conn = P1ConnectionUtil.getConnection()){
			String sql = "delete from ers_reimbursement_status where reim_status_id = ?";
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
	public boolean insertStatus(Status stats) {
		// TODO Auto-generated method stub
		try(Connection conn = P1ConnectionUtil.getConnection()){
			String sql = "insert into ers_reimbursement_status(reimb_status) values (?);";
			//Instantiate a prepared statement to fill in the variable of our sql
			PreparedStatement ps = conn.prepareStatement(sql);
			//Fill in the values of our variables using ps.
			
			ps.setString(1, stats.getStatus());
			
			ps.executeUpdate();//this sends our sql off to the database
			
			System.out.println(stats.getStatus() + " was completed successfully...");
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Insert status failed");
			e.printStackTrace();
		return false;
		}
	}


	@Override
	public ArrayList<Status> getStatus() {
			 
			try(Connection conn = P1ConnectionUtil.getConnection()){
				String sql = "select * from ers_reimbursement_status;";
				//no variables do we don't need a preparedStatement
				//What we use is
				Statement s = conn.createStatement();
				//import statement from java.sql
				//remember to execute 
				ResultSet rs = s.executeQuery(sql);
				
				ArrayList<Status> statsList = new ArrayList<>();
				while(rs.next()) {
					Status stats = new Status(
							rs.getInt("reim_status_id"),
							rs.getString("reimb_status")	
							);
					statsList.add(stats);
				}
		return statsList;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Failed to get status please retry!!!");
		e.printStackTrace();
	}
			return null;
	}

	

}
