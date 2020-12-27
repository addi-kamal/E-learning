package com.elearning.beans;

import java.util.ArrayList;

import javax.faces.bean.SessionScoped;

import com.elearning.db_javaee.CoursOperations;
import com.elearning.db_javaee.UserDAO;

@SessionScoped
public class User {
	String nom;
	String prenom;
	int Currentid;
	String email;
	String password;
	String isAdmin;
    public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
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
	public int getCurrentid() {
		return Currentid;
	}
	public void setCurrentid(int currentid) {
		Currentid = currentid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<User> userList() {
		ArrayList<User> userList = UserDAO.getListUser();
        return userList;
    } 
	public String delete(int id) {
        return UserDAO.delete(id);
    }
}
