package com.elearning.db_javaee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.primefaces.shaded.commons.io.IOUtils;

import com.elearning.beans.Lesson;
import com.elearning.beans.Ressource;

public class RessourceDAO {
	public static Statement stmtObj;
    public static Connection connObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;
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
	public static String saveRessource(String lesson,int idProf,Ressource r,String nom,String prenom) {
		Date actuelle= new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(actuelle);
		String createdBy = nom+" "+prenom;
		PreparedStatement ps, ps2 ;
		int lesson_id;
	    int saveResult = 0;
	    String navigationResult = "";
        try {
			ps = getConnection().prepareStatement("SELECT lesson_id FROM lesson WHERE nom=? ");
			ps.setString(1,lesson);
			ResultSet rs= ps.executeQuery();
			rs.next();
			lesson_id = Integer.parseInt(rs.getString("lesson_id"));
			ps2 =getConnection().prepareStatement("INSERT INTO ressource (nom, createdBy, lesson_id, nameFile,dateCreation) VALUES (?,?,?,?,'"+date+"')");
			ps2.setString(1,r.getNom());
			ps2.setString(2,createdBy);
			ps2.setInt(3,lesson_id);
			String name= (r.getUploadedFile()).getSubmittedFileName();
			ps2.setString(4, name);
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
	public static ArrayList<Ressource> getListRessource(int id) {
        ArrayList<Ressource> ressourceList = new ArrayList<Ressource>();  
        try {
                pstmt = getConnection().prepareStatement("SELECT * FROM ressource WHERE lesson_id=? ");
                pstmt.setInt(1, id);
                ResultSet rs= pstmt.executeQuery();
                while( rs.next()){
                Ressource r= new Ressource();
                r.setRessource_id(rs.getInt("ressource_id")); 
                r.setNom(rs.getString("nom"));  
                r.setDateCreation(rs.getDate("dateCreation"));  
                r.setCreatedBy(rs.getString("createdBy"));  
                r.setLesson_id(rs.getInt("lesson_id"));   
                r.setNomFile(rs.getString("nameFile")); 
                //sessionMapObj.put("id", rs.getInt("cours_id"));
                ressourceList.add(r);  
                //int idCours = r.getId();
                //sessionMapObj.put("id", idCours);
                }  
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return ressourceList;
        
    }
}
