package com.elearning.db_javaee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.elearning.beans.Cours;
import com.elearning.beans.Lesson;

public class CoursOperations {
	public static Statement stmtObj;
    public static Connection connObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt, pstmt2,pstmt3;
 
    public static Connection getConnection(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");     
            String db_url ="jdbc:mysql://localhost:3306/elearning",
                    db_userName = "root",
                    db_password = "hasnae";
            connObj = DriverManager.getConnection(db_url,db_userName,db_password);  
        } catch(Exception sqlException) {  
            sqlException.printStackTrace();
        }  
        return connObj;
    }
	public static String saveCours(Cours c,String nom, String prenom, int id) {
		String prof = nom+" "+prenom;
		Date actuelle= new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(actuelle);
        int saveResult = 0;
        String navigationResult = "";
        try {      
            pstmt = getConnection().prepareStatement("insert into cours (titre, subject, createdBy, language, estimatedTime, prix ,prof_id, dateCreation) values (?, ?, ?, ?, ?, ?,?,'"+date+"')");         
            pstmt.setString(1, c.getTitre());
            pstmt.setString(2, c.getSubject());
            pstmt.setString(3, prof);
            pstmt.setString(4, c.getLanguage());
            pstmt.setString(5, c.getEstimatedTime());
            pstmt.setString(6, c.getPrix());
            pstmt.setInt(7, id);
            saveResult = pstmt.executeUpdate();

            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        if(saveResult !=0) {
            navigationResult = "message.xhtml";
        } else {
            navigationResult = "failed.xhtml";
        }
        return navigationResult;
    }
	
	public static ArrayList<Cours> getListCours(int id) {
        ArrayList<Cours> coursList = new ArrayList<Cours>();  
        try {
                pstmt = getConnection().prepareStatement("SELECT * FROM cours WHERE prof_id=?");
                pstmt.setInt(1, id);
                ResultSet rs= pstmt.executeQuery();
                while( rs.next()){
                Cours c = new Cours();
                c.setId(rs.getInt("id_cours")); 
                c.setProfId(rs.getInt("prof_id"));
                c.setTitre(rs.getString("titre"));  
                c.setSubject(rs.getString("subject"));  
                c.setCreatedBy(rs.getString("createdBy"));  
                c.setDateCreation(rs.getDate("dateCreation"));  
                c.setLanguage(rs.getString("language"));  
                c.setPrix(rs.getString("prix")); 
                c.setEstimatedTime(rs.getString("estimatedTime"));
                
                coursList.add(c);  
                } 
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        } 
        return coursList;
    }

	public static String delete(int coursId){
	        try {
	            pstmt = getConnection().prepareStatement("delete from cours where id_cours = "+coursId);  
	            pstmt.executeUpdate();
	            pstmt2 = getConnection().prepareStatement("delete from lesson where cours_id = "+coursId);  
	            pstmt2.executeUpdate();
	            connObj.close();
	            
	        } catch(Exception sqlException){
	            sqlException.printStackTrace();
	        }
	        return "/ListCours.xhtml?faces-redirect=true";  
	    }
}
	
