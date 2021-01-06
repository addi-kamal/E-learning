package com.elearning.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.elearning.beans.Course;
import com.elearning.db.Connexion;
import com.elearning.beans.Lesson;

public class LessonDAO {
	public static Statement stmtObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;
 
	public static String addLesson(Lesson l, String cours, String prof) {
		
		Date actuelle= new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(actuelle);
        int saveResult = 0;
        String navigationResult = "";
        Connection conn=null;
        try {
        	conn = new Connexion().ConnectToDB();		
            pstmt = conn.prepareStatement("insert into lesson (lesson_id, titre, cours_id, CreatedBy ,text, dateCreation) values (?, ?, ?, ?, ?,'"+date+"')");         
            pstmt.setString(1, l.getId());
            pstmt.setString(2, l.getTitre());
            pstmt.setString(3, l.getCours_id());
            pstmt.setString(4, prof);
            pstmt.setString(5, l.getText());
            saveResult = pstmt.executeUpdate();
            conn.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        if(saveResult !=0) {
            navigationResult = "done.xhtml";
        } else {
            navigationResult = "error.xhtml";
        }
        return navigationResult;
    }
	
	
	public static ArrayList<Lesson> getListLesson(String id) {
		//System.out.println("Cours id " + id);
        ArrayList<Lesson> lessonList = new ArrayList<Lesson>();  
        Connection conn=null;
        try {
        	    conn = new Connexion().ConnectToDB();	
                pstmt = conn.prepareStatement("SELECT * FROM lesson l,users u WHERE u.id=l.CreatedBy AND cours_id=? ");
                pstmt.setString(1, id);
                ResultSet rs= pstmt.executeQuery();
                while( rs.next()){
                Lesson l = new Lesson();
                l.setId(rs.getString("lesson_id")); 
                l.setTitre(rs.getString("titre"));  
                l.setDateCreation(rs.getDate("dateCreation"));  
                l.setCreatedBy(rs.getString("CreatedBy"));  
                l.setCours_id(rs.getString("cours_id")); 
                l.setText(rs.getString("text"));
                //sessionMapObj.put("id", rs.getInt("cours_id"));
                lessonList.add(l);  
                //int idCours = l.getId();
                //sessionMapObj.put("id", idCours);
                }  
                conn.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return lessonList;
        
    }
	public static Lesson getLesson(String id) {
        Lesson l = new Lesson();

        try {
        	    Connection conn = new Connexion().ConnectToDB();	
                pstmt = conn.prepareStatement("SELECT * FROM lesson  WHERE lesson_id=? ");
                pstmt.setString(1, id);
                ResultSet rs= pstmt.executeQuery();
                rs.next();
                Lesson l1 = new Lesson();
                l1.setId(rs.getString("lesson_id")); 
                l1.setTitre(rs.getString("titre"));  
                l1.setDateCreation(rs.getDate("dateCreation"));  
                l1.setCreatedBy(rs.getString("CreatedBy"));  
                l1.setCours_id(rs.getString("cours_id")); 
                l1.setText(rs.getString("text"));
                conn.close();
                l=l1;
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return l;
        
    }
}