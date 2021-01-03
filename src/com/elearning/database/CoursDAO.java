package com.elearning.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.elearning.beans.Course;
import com.elearning.beans.Bean;
import com.elearning.db.Connexion;

public class CoursDAO {
	public static Statement stmtObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;
 
	public static String addCours(Course c, String prof) {
		
		Date actuelle= new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(actuelle);
        int saveResult = 0;
        String navigationResult = "";
        Connection conn=null;
        try {
        	conn = new Connexion().ConnectToDB();		
            pstmt = conn.prepareStatement("insert into cours (id_niveau, id_sujet,estimatedTime,prix ,titre, CreatedBy, Description, language, dateCreation) values (?, ?, ?, ?, ?, ?,?,?,'"+date+"')");         
            pstmt.setString(1, c.getId_niveau());
            pstmt.setString(2, c.getId_sujet());
            pstmt.setString(3, c.getEstimated_time());
            pstmt.setString(4, c.getPrix());
            pstmt.setString(5, c.getTitre());
            pstmt.setString(6, prof);
            pstmt.setString(7, c.getDesc()); 
            pstmt.setString(8, c.getLanguage());
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
	
    public ArrayList<Bean> lister() throws SQLException{
    ArrayList<Bean> liste = null;
    Connection conn=null;
    	conn = new Connexion().ConnectToDB();		
        pstmt = conn.prepareStatement("SELECT COUNT(id_sujet) AS nombre,id_sujet FROM cours GROUP BY id_sujet;");  
        resultSetObj =pstmt.executeQuery();
        liste = new ArrayList<Bean>();
        while( resultSetObj.next()) {
        	Bean b = new Bean();
            b.setNombre(resultSetObj.getInt("nombre")); 
            b.setSujet_id(resultSetObj.getString("id_sujet"));
            liste.add(b); 
        	
        }
        conn.close(); 
   
    return liste;
}

}