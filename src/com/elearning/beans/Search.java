package com.elearning.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.elearning.db.Connexion;

@ManagedBean
@RequestScoped
public class Search implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String query;
	private ArrayList<Course> courses;
	

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	
	public String submit() {
		return "resp.xhtml";
	}
	
	/***
	 * La méthode recherche des chaînes correspondantes dans la table `cours` dans la bd
	 * et enregistre les resultats dans ArrayList courses 
 	 * **/
	
	public String lookup() {
		System.out.println("In lookup!!");
	    try {
			 Connection connection = new Connexion().ConnectToDB();
			 String sql = "SELECT * FROM cours WHERE titre LIKE ?"; 
			 PreparedStatement stm = connection.prepareStatement(sql);
			 stm.setString(1, "%" + query + "%");
			 ResultSet results = stm.executeQuery();
			 Course cours; 
			 courses = new ArrayList<Course>();
			 Instructeur prof;
			 
			 while(results.next()) {
				 cours = new Course();
				 prof = new Instructeur();
				 cours.setId(results.getInt("id_cours"));
				 cours.setCreatedBy(results.getInt("CreatedBy"));
				 //Info de prof 
				 sql = "SELECT * FROM users WHERE id ="+cours.getCreatedBy();
				 Statement stat = connection.createStatement();
				 ResultSet res = stat.executeQuery(sql);
				 if(res.next()) {
					 prof.setNom(res.getString("nom"));
					 prof.setPrenom(res.getString("prenom"));
				 } else {
					 prof.setNom("Inconnu");
					 prof.setPrenom("Inconnu");
				 }
				 cours.setProf(prof);
				 cours.setTitre(results.getString("titre"));
				 System.out.println(cours.getTitre());
				 cours.setDateCreation(results.getDate("dateCreation"));
				 courses.add(cours);
			 }
			 	 
	    } catch(SQLException e) {
			e.printStackTrace();
		}
	    
	    return "resp.xhtml";
	}
	
	
	
	
}
