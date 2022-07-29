package com.melvis.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.melvis.models.Reimbursement;
import com.melvis.models.Status;
import com.melvis.models.Type;
import com.melvis.utils.P1ConnectionUtil;

public class ReimbursementDAO implements ReimbursementDAOInterface{

		@Override
		public boolean insertReimburse(Reimbursement reimburse) {
			// TODO Auto-generated method stub
			try(Connection conn = P1ConnectionUtil.getConnection()){
	        String sql = "insert into ers_reimbursement(reimb_amount_$, reimb_description, reimb_author, reimb_resolver, reimb_status_id_fk, reimb_type_id_fk, reimb_submitted, reimb_resolved) values (?,?,?,?,?,?,?,?);";
				//Instantiate a prepared statement to fill in the variable of our sql
				PreparedStatement ps = conn.prepareStatement(sql);
				//Fill in the values of our variables using ps.
				ps.setDouble(1, reimburse.getAmount());
				ps.setString(2, reimburse.getDescription());
				ps.setInt(3, reimburse.getAuthor());
				ps.setInt(4, reimburse.getResolver());
				ps.setInt(5, reimburse.getStatus_id_fk());
				ps.setInt(6, reimburse.getType_id_fk());
				ps.setDate(7, (Date) reimburse.getSubmitted());
				ps.setDate(8, (Date) reimburse.getResolved());
				ps.executeUpdate();//this sends our sql off to the database
				
				System.out.println("Reimbursement was inserted successfully...");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Reimbursement Insert failed");
				e.printStackTrace();
			}
			return false;
		}
		
		//This method gets all diagnosis from the DB
	    
		public ArrayList<Reimbursement> getReimbursement() {
	 
			try(Connection conn = P1ConnectionUtil.getConnection()){
				String sql = "select * from ers_reimbursement;";
				//no variables do we don't need a preparedStatement
				//What we use is
				Statement s = conn.createStatement();
				//import statement from java.sql
				//remember to execute 
				ResultSet rs = s.executeQuery(sql);
				
				ArrayList<Reimbursement> reimbursementList = new ArrayList<>();
				// us rs.next()in a while loop to create diagnosis object and populate our ArrayList
				
				while(rs.next()) {
					Reimbursement reimb = new Reimbursement(
							rs.getInt("reimb_id"),
							rs.getDouble("reimb_amount_$"),
							rs.getString("reimb_description"),
							rs.getInt("reimb_author"),
							rs.getInt("reimb_resolver"),
							rs.getDate("reimb_submitted"),
							rs.getDate("reimb_resolved"),
							null,
							null
							
							);
					
					int statusFK = rs.getInt("reimb_status_id_fk");
					StatusDAO sDAO = new StatusDAO();
					
					
					Status s1 = sDAO.getStatusById(statusFK);
					
				
					reimb.setStatus(s1);
				
					int typeFK = rs.getInt("reimb_type_id_fk");
					TypeDAO dDAO = new TypeDAO();
					
				
					Type type = dDAO.getTypeById(typeFK);
					
					
					reimb.setType(type);
					reimbursementList.add(reimb);
					
				}
				return reimbursementList;//once the while loop breaks 
				
			}catch(SQLException e) {
				System.out.println("Something went wrong getting reimbursement!");
				e.printStackTrace();
			}
			return null;
		}
		
		public boolean deleteReimbursementId(int id) {
			try (Connection conn = P1ConnectionUtil.getConnection()){
				String sql = "delete from ers_reimbursement where reimb_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				
				ps.executeUpdate();
				System.out.println(id + " has been deleted successfully... ");
				return true;
			}catch(SQLException e)	{
				System.out.println("Failed to delete!");
				e.printStackTrace();
			}
			
			return false;
		

		}

		
		public boolean updateReimbursementAmount(Double amount, int id) {
			// TODO Auto-generated method stub
			try (Connection conn = P1ConnectionUtil.getConnection()){
				String sql = "update ers_reimbursement set reimb_amount = ? where reimb_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setDouble(1, amount);
				ps.setInt(2, id);
				ps.executeUpdate();
				System.out.println( "Your amount have been updated to " + amount);
				return true;
			}catch(SQLException e)	{
				System.out.println("Failed to update amount");
				e.printStackTrace();
			}
			return false;
		}

}
