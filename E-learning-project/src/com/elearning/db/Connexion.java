package com.elearning.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	public Connection ConnectToDB() {
		String jdbcURL = "jdbc:mysql://localhost:3306/elearning?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	    String dbUser = "root";
	    String dbPassword = "rax5cv3bta7";
	    try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
			 System.out.println("Success");
			 return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
