package com.elearning.posts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.elearning.posts.Post;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Posts {
	
	ArrayList<Post> postsList = new ArrayList<>();
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/MyDBUsers?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	// database credentials
	String USER = "JDBCUser";
	String PASS = "PWDUSER";
	
	
	public Posts() {
		fillPosts();
		
	}
	
	public void fillPosts() {
		// create connection to our local database
			Connection conn = null;		
			Statement stmt = null;
			try {
				Class.forName(JDBC_DRIVER); 
				conn = DriverManager.getConnection(DB_URL, USER, PASS);			
				stmt = conn.createStatement();
				String sql;
				sql = "SELECT * FROM Posts;";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					Post p = new Post();
					p.name = rs.getString("user");
					p.text = rs.getString("text");
					p.time = rs.getString("time").toString();
					
					postsList.add(p);
				}
				rs.close();
				stmt.close();
				conn.close();
				
			} catch (Exception se) {
				
			}	
	}


	public ArrayList<Post> getPostsList() {
		return postsList;
	}

	public void setPostsList(ArrayList<Post> postsList) {
		this.postsList = postsList;
	}
	
	

}
