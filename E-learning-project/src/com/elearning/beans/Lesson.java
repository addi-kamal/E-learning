package com.elearning.beans;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.elearning.database.LessonDAO;
@ManagedBean(name="lesson")
@RequestScoped
public class Lesson {
	private String id;
	private String titre;
	private Date dateCreation;
	private String cours_id;
	private String createdBy;
	private String text;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getCours_id() {
		return cours_id;
	}
	public void setCours_id(String cours_id) {
		this.cours_id = cours_id;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public static String addLesson(Lesson l, String cours, String prof) {
		return LessonDAO.addLesson(l,prof,cours);
	}
	public ArrayList<Lesson> lessonList(String id) {
		ArrayList<Lesson> lessonList = LessonDAO.getListLesson(id);
		System.out.println(lessonList);
        return lessonList;
	}
	public Lesson AfficheLesson(String id) {
		Lesson lessonList = LessonDAO.getLesson(id);
		System.out.println(lessonList.getText());
        return lessonList;
	}
}