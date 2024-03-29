package com.elearning.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.elearning.db.Connexion;

import java.sql.*;

@ManagedBean
@SessionScoped
public class ConnectStudent {
	String email;
	String password;
	
	// JDBC driver name and database URL
	//static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/elearning?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	// database credentials
	//String USER = "root";
	//String PASS = "rax5cv3bta7";
	
	public ConnectStudent() {	
	}
	
	public String verify() {
		// create connection to local database
			Connection conn = null;		
			Statement stmt = null;
			boolean verified = false;
			try {
				conn = new Connexion().ConnectToDB();
				stmt = conn.createStatement();
				String sql;
				sql = "SELECT `id` FROM `users` WHERE `email` = "+"'"+email+"' AND `password` = '"+password+"';";
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next())
					verified = true;
				rs.close();
				stmt.close();
				conn.close();
				
			} catch (Exception se) {
				return "erroe.xhtml";
			}
			if(verified) {		
				return "todo.xhtml";
			}
			else
				return null;
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
