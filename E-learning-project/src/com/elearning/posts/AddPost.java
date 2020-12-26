package com.elearning.posts;


import java.sql.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.elearning.beans.*;

@ManagedBean
@RequestScoped
public class AddPost {
	Student student = new Student();
	String nom =student.getNom();
	String prenom = student.getPrenom();
	String text;
	String time ="NOW()";
	String id = student.getCNE();
	String user = nom+prenom;
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/MyDBUsers?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	// database credentials
	String USER = "JDBCUser";
	String PASS = "PWDUSER";
	
	public void addPostm() {
		Connection conn = null;	
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER); 
			System.out.println("connection to db");
			// create connection to our local database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sql = "INSERT INTO Posts" + " (id, text, time, user) VALUES" + " ('"+id+"','"+text+"',"+time+",'"+user+"');";
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
}
