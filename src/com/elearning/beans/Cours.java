package com.elearning.beans;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.elearning.db_javaee.CoursOperations;
import com.elearning.db_javaee.LessonDAO;
@ManagedBean(name="cours")
@RequestScoped
public class Cours {
	private int id;
	private int profId;
	private String titre;
	private String subject;
	private String createdBy;
	private Date dateCreation;
	private String language;
	private String estimatedTime;
	private String prix;
	public ArrayList<Cours> coursList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	
	public int getProfId() {
		return profId;
	}
	public void setProfId(int profId) {
		this.profId = profId;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public ArrayList<Cours> coursList(int id) {
		ArrayList<Cours> coursList = CoursOperations.getListCours(id);
        return coursList;
    } 
	public String saveCours(Cours c, String nom, String prenom, int id) {
        return CoursOperations.saveCours(c, nom, prenom, id);
    }
	public String delete(int id) {
        return CoursOperations.delete(id);
    }	
}
