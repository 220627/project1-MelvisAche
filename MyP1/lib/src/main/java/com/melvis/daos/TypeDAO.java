package com.melvis.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.melvis.models.Type;
import com.melvis.utils.P1ConnectionUtil;

public class TypeDAO implements TypeDAOInterface{

//	public Type getTypeById(int typeFK) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public Type getTypeById(int id) {
	try (Connection conn = P1ConnectionUtil.getConnection()){
		String sql = "select * from ers_reimbursement_type where reimb_type_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery(); //execute the query into our new resultset
		while(rs.next()) {
			Type type = new Type(
					rs.getInt("reimb_type_id"),
					rs.getString("reimb_type")
					);
			return type;
		}
		
	}catch(SQLException e)	{
		System.out.println("Get type failed");
		e.printStackTrace();
	}
	return null;
	}


	public boolean updateReimbType(String type, int id) {
		// TODO Auto-generated method stub
		try (Connection conn = P1ConnectionUtil.getConnection()){
			String sql = "update ers_reimbursement_type set reimb_type = ? where reimb_type_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Reimbursement type has been updated successfully!");
			return true;
		}catch(SQLException e)	{
			System.out.println("Failed to update reimbursement type.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteTypeId(int id) {
		try (Connection conn = P1ConnectionUtil.getConnection()){
			String sql = "delete from ers_reimbursement_type where reimb_type_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println(id + " has been deleted successfully!!! ");
			return true;
		}catch(SQLException e)	{
			System.out.println("Failed to delete...");
			e.printStackTrace();
		}
		
		return false;
	}


	@Override
	public boolean insertType(Type type) {
		// TODO Auto-generated method stub
		try(Connection conn = P1ConnectionUtil.getConnection()){
			String sql = "insert into ers_reimbursement_type(reimb_type) values (?);";
			//Instantiate a prepared statement to fill in the variable of our sql
			PreparedStatement ps = conn.prepareStatement(sql);
			//Fill in the values of our variables using ps.
			
			ps.setString(1, type.getType());
			
			ps.executeUpdate();//this sends our sql off to the database
			
			System.out.println(type.getType() + " was completed successfully...");
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Insert reimbursement type failed");
			e.printStackTrace();
		return false;
		}
	}


	@Override
	public ArrayList<Type> getType() {
			 
			try(Connection conn = P1ConnectionUtil.getConnection()){
				String sql = "select * from ers_reimbursement_type;";
				//no variables do we don't need a preparedStatement
				//What we use is
				Statement s = conn.createStatement();
				//import statement from java.sql
				//remember to execute 
				ResultSet rs = s.executeQuery(sql);
				
				ArrayList<Type> typeList = new ArrayList<>();
				while(rs.next()) {
					Type type = new Type(
							rs.getInt("reimb_type_id"),
							rs.getString("reimb_type")	
							);
					typeList.add(type);
				}
		return typeList;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Failed to get reimbursement type please retry!!!");
		e.printStackTrace();
	}
			return null;
	}


}
