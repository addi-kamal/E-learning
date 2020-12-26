package com.elearning.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;




@ManagedBean
@RequestScoped
public class Test implements Serializable {
	
	String nom, prenom, email;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String submit() {
		return "response.xhtml";
	}
	
	
	
	
	
	
	
	
	
	
}
