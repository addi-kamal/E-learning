package com.elearning.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.elearning.db_javaee.CoursOperations;
import com.elearning.db_javaee.LessonDAO;
@ManagedBean(name="lesson")
@RequestScoped
public class Lesson {
	private int id;
	private String nom;
	private String cours;
	private Date dateCreation;
	private int cours_id;
	private String createdBy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String titre) {
		this.nom = titre;
	}
	public String getCours() {
		return cours;
	}
	public void setCours(String cours) {
		this.cours = cours;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public int getCours_id() {
		return cours_id;
	}
	public void setCours_id(int cours_id) {
		this.cours_id = cours_id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String saveLesson(String cours,int idProf,Lesson l, String nom, String prenom) {
        return LessonDAO.saveLesson(cours,idProf,l,nom,prenom);
    }
	public ArrayList<Lesson> lessonList(int id) {
		ArrayList<Lesson> lessonList = LessonDAO.getListLesson(id);
		System.out.println(lessonList);
        return lessonList;
    } 
	public String delete(int id) {
        return LessonDAO.delete(id);
    }
	
}