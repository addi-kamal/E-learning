package com.elearning.database;

import java.sql.*;
import com.elearning.beans.Student;
import com.elearning.beans.User;
import com.elearning.db.Connexion;


public class ConnectJDBC {
	public static User insertStudent(User student) {
		Connection conn = null;		
		PreparedStatement stmt = null;
		try {
			//Connecting to database
			User new_user = new User();
			conn = new Connexion().ConnectToDB();			
			String sql = "INSERT INTO users"
			+"(email, password, prenom, nom, telephone, ville, pays, niveau, dateNaissance, code_postal) VALUES"
					+"(?,?,?,?,?,?,?,?,?,?);";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			stmt.setString(1, student.getEmail());
			stmt.setString(2, student.getPassword());
			stmt.setString(3, student.getPrenom());
			stmt.setString(4, student.getNom());
			stmt.setString(5, student.getTelephone());
			//stmt.setString(6, student.getCNE());
			stmt.setString(6, student.getVille());
			stmt.setString(7, student.getPays());
			stmt.setString(8, student.getNiveau());
			stmt.setString(9, student.getDateNaissance());
			//stmt.setString(10, student.getSexe());
			stmt.setString(10, student.getCode_postal());

			Integer affectedRows = stmt.executeUpdate();		
			int idNewRows;
			if(affectedRows == 0) {
				throw new SQLException("Creating row failed, no row affected");
			}
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					idNewRows = generatedKeys.getInt(1);
					System.out.println("id of new object :"+idNewRows);
				}
				else {
					throw new SQLException("Creating failed, no id obtained");
				}
			}
			student.setCurrentid(idNewRows);
			
			stmt.close();
			conn.close();
			return student;
				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		  try {
			  if(stmt!=null)
				  stmt.close();
		  } catch(SQLException se2) {
		  }
		  try {
			  if(conn!=null)
				  conn.close();
		  }catch(SQLException se) {
			  se.printStackTrace();
		  }
		}
	}
	public static User insertAdminProf(User admin) {
		Connection conn = null;		
		PreparedStatement stmt = null;
		try {
			//Connecting to database
			User new_user = new User();
			conn = new Connexion().ConnectToDB();			
			String sql = "INSERT INTO users"
			+"(email, password, prenom, nom, telephone, ville, pays, niveau, dateNaissance, code_postal,user_type) VALUES"
					+"(?,?,?,?,?,?,?,?,?,?,?);";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			stmt.setString(1, admin.getEmail());
			stmt.setString(2, admin.getPassword());
			stmt.setString(3, admin.getPrenom());
			stmt.setString(4, admin.getNom());
			stmt.setString(5, admin.getTelephone());
			//stmt.setString(6, student.getCNE());
			stmt.setString(6, admin.getVille());
			stmt.setString(7, admin.getPays());
			stmt.setString(8, admin.getNiveau());
			stmt.setString(9, admin.getDateNaissance());
			//stmt.setString(10, student.getSexe());
			stmt.setString(10, admin.getCode_postal());
            stmt.setString(11, admin.getUser_type());
			Integer affectedRows = stmt.executeUpdate();		
			int idNewRows;
			if(affectedRows == 0) {
				throw new SQLException("Creating row failed, no row affected");
			}
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					idNewRows = generatedKeys.getInt(1);
					System.out.println("id of new object :"+idNewRows);
				}
				else {
					throw new SQLException("Creating failed, no id obtained");
				}
			}
			admin.setCurrentid(idNewRows);
			
			stmt.close();
			conn.close();
			return admin;
				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		  try {
			  if(stmt!=null)
				  stmt.close();
		  } catch(SQLException se2) {
		  }
		  try {
			  if(conn!=null)
				  conn.close();
		  }catch(SQLException se) {
			  se.printStackTrace();
		  }
		}
	}	
	
}