package com.elearning.db_javaee;

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
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;

import com.elearning.beans.Cours;
import com.elearning.beans.Lesson;

public class LessonDAO {
	public static Statement stmtObj;
    public static Connection connObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;
    private static int param1;
    private String param2;
    private static String param3;
    private static String param4;
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
	public static String saveLesson(String cours,int idProf,Lesson l,String nom,String prenom) {
		//Map<String,String> parameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		//String id = parameterMap.get("parameter");
		Date actuelle= new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(actuelle);
		String createdBy = nom+" "+prenom;
		PreparedStatement ps, ps2 ;
		int cours_id;
	    int saveResult = 0;
	    String navigationResult = "";
        try {
			ps = getConnection().prepareStatement("SELECT id_cours FROM cours WHERE titre=? and prof_id=?");
			ps.setString(1,cours);
			ps.setInt(2, idProf);
			ResultSet rs= ps.executeQuery();
			rs.next();
			cours_id = Integer.parseInt(rs.getString("id_cours"));
			ps2 =getConnection().prepareStatement("INSERT INTO lesson (nom, createdBy, cours_id, dateCreation) VALUES (?,?,?,'"+date+"')");
			ps2.setString(1,l.getNom());
			ps2.setString(2,createdBy);
			ps2.setInt(3,cours_id);
			saveResult=ps2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 if(saveResult !=0) {
		            navigationResult = "message.xhtml";
		        } else {
		            navigationResult = "failed.xhtml";
		        }
			 return navigationResult; 
	}
	public static ArrayList<Lesson> getListLesson(int id) {
		//System.out.println("Cours id " + id);
        ArrayList<Lesson> lessonList = new ArrayList<Lesson>();  
        //Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        try {
                pstmt = getConnection().prepareStatement("SELECT * FROM lesson WHERE cours_id=? ");
                pstmt.setInt(1, id);
                ResultSet rs= pstmt.executeQuery();
                while( rs.next()){
                Lesson l = new Lesson();
                l.setId(rs.getInt("lesson_id")); 
                l.setNom(rs.getString("nom"));  
                l.setDateCreation(rs.getDate("dateCreation"));  
                l.setCreatedBy(rs.getString("createdBy"));  
                l.setCours_id(rs.getInt("cours_id"));   
                //sessionMapObj.put("id", rs.getInt("cours_id"));
                lessonList.add(l);  
                //int idCours = l.getId();
                //sessionMapObj.put("id", idCours);
                }  
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return lessonList;
        
    }
	public static String delete(int lessonId){
        try {
            pstmt = getConnection().prepareStatement("delete from lesson where lesson_id = "+lessonId);  
            pstmt.executeUpdate();  
            connObj.close();
        } catch(Exception sqlException){
            sqlException.printStackTrace();           
        }
        return "/listLesson.xhtml?faces-redirect=true";
    }
	
}
