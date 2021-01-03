package com.elearning.posts;


import java.sql.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

import com.elearning.beans.*;

@ManagedBean
@RequestScoped
public class AddPost {
	/*Student student = new Student();
	String nom =student.getNom();
	String prenom = student.getPrenom();*/
	//String statut;
	String text;
	String time ="NOW()";
	//String user = nom+prenom;
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/elearning?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	// database credentials
	String USER = "root";
	String PASS = "";
	
	public void addPostm(User user) {
		Connection conn = null;	
		Statement stmt = null;
		System.out.println(user.getNom());
		String nom =user.getNom();
		String prenom = user.getPrenom();
		String user1 = nom+' '+prenom;
		/*if(user.getUser_type().equals("A")) {statut="Admin";}
		else if(user.getUser_type().equals("P")) {statut="Professeur";}
		else {statut = "Etudiant";}*/
		try {
			Class.forName(JDBC_DRIVER); 
			System.out.println("connection to db");
			// create connection to our local database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "INSERT INTO Posts" + " (text, time, user) VALUES" + " ('"+text+"',"+time+",'"+user1+"');";
			//String sql = "INSERT INTO Posts" + " (text, time, user,statut) VALUES" + " ('"+text+"',"+time+",'"+user1+"','"+statut+"');";
			stmt = conn.createStatement();
			
			

			Integer affectedRows = stmt.executeUpdate(sql);		
			if(affectedRows == 0) {
				throw new SQLException("Creating row failed, no row affected");
			}
			
			stmt.close();
			conn.close();
			System.out.println("post created !");
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
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
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}