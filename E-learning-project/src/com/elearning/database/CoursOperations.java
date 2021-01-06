package com.elearning.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.elearning.beans.*;

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
                    db_password = "";
            connObj = DriverManager.getConnection(db_url,db_userName,db_password);  
        } catch(Exception sqlException) {  
            sqlException.printStackTrace();
        }  
        return connObj;
    }
	public static String saveCours(Course c, String id) {
		Date actuelle= new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(actuelle);
        int saveResult = 0;
        String navigationResult = "";
        try {      
            pstmt = getConnection().prepareStatement("insert into cours (titre,id_sujet,CreatedBy,language, estimatedTime,prix,dateCreation,Description) values (?,?,?,?,?,?,'"+date+"',?)"); 
            //(titre,id_sujet,CreatedBy,language, estimatedTime,prix,dateCreation,Description) values (?,?,?,?,?,?,'"+date+"',?)"); 
            pstmt.setString(1, c.getTitre());
            pstmt.setString(2, c.getId_sujet());
            pstmt.setString(3, c.getCreatedby());
            pstmt.setString(4, c.getLanguage());
            pstmt.setString(5, c.getEstimated_time());
            pstmt.setString(6, c.getPrix());
            pstmt.setString(8, c.getDesc());
            saveResult = pstmt.executeUpdate();

            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        if(saveResult !=0) {
            navigationResult = "/Professeur/message.xhtml";
        } else {
            navigationResult = "/Professeur/failed.xhtml";
        }
        return navigationResult;
    }

	public static ArrayList<Course> getListAllCourse() throws SQLException{
		ArrayList<Course> CourseAllList = new ArrayList<Course>();
		pstmt = getConnection().prepareStatement("select * from cours c ,Subjects s , users U Where c.id_sujet = s.id AND c.CreatedBy=U.id;");
        ResultSet rs= pstmt.executeQuery();
        while(rs.next()) {
        	Course c = new Course();
        	c.setId_cours(rs.getString("id_cours"));
        	c.setDatecreation(rs.getDate("datecreation"));
        	c.setId_sujet(rs.getString("Sujet"));
        	c.setTitre(rs.getString("titre"));
        	c.setCreatedby(rs.getString("nom")+" "+rs.getString("prenom"));
        	c.setLanguage(rs.getString("language"));
        	c.setId_niveau(rs.getString("id_niveau"));
        	c.setEstimated_time(rs.getString("estimatedTime"));
        	c.setDesc(rs.getString("Description"));
        	c.setPrix(rs.getString("prix"));
        	CourseAllList.add(c);
        }
		return CourseAllList;
	}
	
	public static ArrayList<Course> getListCourse(String id) throws SQLException {
        ArrayList<Course> CourseList = new ArrayList<Course>();
		pstmt = getConnection().prepareStatement("select * from cours c ,Subjects s  Where c.id_sujet = s.id AND CreatedBy= ?;");
        pstmt.setString(1, id);
        ResultSet rs= pstmt.executeQuery();
        while(rs.next()) {
        	Course c = new Course();
        	c.setId_cours(rs.getString("id_cours"));
        	c.setDatecreation(rs.getDate("datecreation"));
        	c.setId_sujet(rs.getString("Sujet"));
        	c.setTitre(rs.getString("titre"));
        	//c.setCreatedby(rs.getString("CreatedBy"));
        	c.setLanguage(rs.getString("language"));
        	c.setId_niveau(rs.getString("id_niveau"));
        	c.setEstimated_time(rs.getString("estimatedTime"));
        	c.setDesc(rs.getString("Description"));
        	c.setPrix(rs.getString("prix"));
        	CourseList.add(c);
        }
        return CourseList;
    }

	public static String delete(int CourseId){
	        return "/ListCours.xhtml?faces-redirect=true";  
	    }
}