package com.elearning.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.elearning.beans.User;

//Check login : 
public class UserDAO {
	public static Statement stmtObj;
    public static Connection connObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;
	public static Connection getConnection(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");     
            String db_url ="jdbc:mysql://localhost:3306/elearning",
                    db_userName = "root",
                    db_password = "";
            connObj = DriverManager.getConnection(db_url,db_userName,db_password);  
        } catch(Exception sqlException) {  
            sqlException.printStackTrace();
        }  
        return connObj;
    }
	 
    public User checkLogin(String email, String password) throws SQLException,
            ClassNotFoundException {
        String sql = "SELECT * FROM users WHERE email = ? and password = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
 
        ResultSet result = statement.executeQuery();
 
        User user = null;
 
        if (result.next()) {
            user = new User();
            user.setCurrentid(result.getInt("id"));
            user.setEmail(email);
            user.setNom(result.getString("nom"));
            user.setPrenom(result.getString("prenom"));
            user.setUser_type(result.getString("user_type"));
        }
 
        connObj.close();
 
        return user;
    }
    
    public static ArrayList<User> getListUser() {
        ArrayList<User> userList = new ArrayList<User>();  
        try {   
        	    pstmt = getConnection().prepareStatement("SELECT * FROM users");
                ResultSet rs= pstmt.executeQuery();
                while( rs.next()){
                User u = new User();
                u.setNom(rs.getString("nom")); 
                u.setPrenom(rs.getString("prenom"));
                u.setCurrentid(rs.getInt("id"));  
                u.setEmail(rs.getString("email"));  
                u.setPassword(rs.getString("password"));  
                u.setUser_type(rs.getString("user_type"));
                userList.add(u);  
                } 
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        } 
        return userList;
    }
    public static String delete(int id) {
        ArrayList<User> userList = new ArrayList<User>();  
        try {   
        	    pstmt = getConnection().prepareStatement("delete from users where id = ?");
        	    pstmt.setInt(1, id);
        	    //pstmt.setInt(2,);
                pstmt.executeUpdate();
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        } 
        //return "/Admin/listUser.xhtml?faces-redirect=true";
        return "/profil?faces-redirect=true";
    }
    
}