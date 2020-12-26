package com.elearning.beans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.elearning.common.Instructeur;

@ManagedBean
@RequestScoped
public class Course implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int id_niveau;
	//private int id_sujet;
	private int createdBy;
	private Instructeur prof;
	private Date dateCreation = new Date();
	private String titre;
	
	public int getId_niveau() {
		return id_niveau;
	}

	public void setId_niveau(int id_niveau) {
		this.id_niveau = id_niveau;
	}
	
	public Instructeur getProf() {
		return prof;
	}

	public void setProf(Instructeur prof) {
		this.prof = prof;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	} 
}
