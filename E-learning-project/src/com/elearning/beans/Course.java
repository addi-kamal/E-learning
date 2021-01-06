package com.elearning.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.elearning.database.CoursDAO;
import com.elearning.database.CoursOperations;

@ManagedBean
@RequestScoped
public class Course implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id_cours;
	String id_niveau;
	String id_sujet;
	Date datecreation = new Date();
	String estimated_time;
	String prix;
	String titre;
	String language;
	String createdby;
	String desc;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public ArrayList<Course> coursList;
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getId_cours() {
		return id_cours;
	}
	public void setId_cours(String id_cours) {
		this.id_cours = id_cours;
	}
	public String getId_niveau() {
		return id_niveau;
	}
	public void setId_niveau(String id_niveau) {
		this.id_niveau = id_niveau;
	}
	public String getId_sujet() {
		return id_sujet;
	}
	public void setId_sujet(String id_sujet) {
		this.id_sujet = id_sujet;
	}
	public Date getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}
	public String getEstimated_time() {
		return estimated_time;
	}
	public void setEstimated_time(String estimated_time) {
		this.estimated_time = estimated_time;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ArrayList<Course> coursList(String id) throws SQLException {
		ArrayList<Course> coursList = CoursOperations.getListCourse(id);
        return coursList;
    } 
	public ArrayList<Course> coursAllList() throws SQLException{
		ArrayList<Course> coursAllList = CoursOperations.getListAllCourse();
		return coursAllList;
	}
	public String saveCours(Course c, String id) {
        return CoursDAO.addCours(c, id);
    }
	public String delete(int id) {
        return CoursOperations.delete(id);
    }
}