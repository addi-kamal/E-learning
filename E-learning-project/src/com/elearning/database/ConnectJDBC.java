package com.elearning.database;

import java.sql.*;
import com.elearning.beans.Student;
public class ConnectJDBC {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/MyDBUsers?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	// database credentials
	String USER = "JDBCUser";
	String PASS = "PWDUSER";
	
	public long insertStudent(Student student) {
		Connection conn = null;		
		PreparedStatement stmt = null;
		try {
			Class.forName(JDBC_DRIVER); 
			System.out.println("insertStudent() connecting....");	
			// create connection to our local database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "INSERT INTO students"
			+"(email, password, prenom, nom, telephone, CNE, ville, pays, niveau, dateNaissance, sexe, code_postal) VALUES"
					+"(?,?,?,?,?,?,?,?,?,?,?,?);";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			stmt.setString(1, student.getEmail());
			stmt.setString(2, student.getPassword());
			stmt.setString(3, student.getPrenom());
			stmt.setString(4, student.getNom());
			stmt.setString(5, student.getTelephone());
			stmt.setString(6, student.getCNE());
			stmt.setString(7, student.getVille());
			stmt.setString(8, student.getPays());
			stmt.setString(9, student.getNiveau());
			stmt.setString(10, student.getDateNaissance());
			stmt.setString(11, student.getSexe());
			stmt.setString(12, student.getCode_postal());

			Integer affectedRows = stmt.executeUpdate();		
			Long idNewRows;
			if(affectedRows == 0) {
				throw new SQLException("Creating row failed, no row affected");
			}
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					idNewRows = generatedKeys.getLong(1);
					System.out.println("id of new object :"+idNewRows);
				}
				else {
					throw new SQLException("Creating failed, no id obtained");
				}
			}
			stmt.close();
			conn.close();
			return idNewRows;
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return 0;
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
