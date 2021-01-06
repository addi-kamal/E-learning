package com.elearning.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.elearning.database.UserDAO;

@ManagedBean
@SessionScoped
public class User {
	private String nom, prenom, email, code_postal, password ,type, niveau, urlavatar ,isVerified ,telephone, pays, ville, dateNaissance,user_type,sexe;
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	private int currentid;
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
    public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}
	public String getUrlavatar() {
		return urlavatar;
	}
	public void setUrlavatar(String urlavatar) {
		this.urlavatar = urlavatar;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		return currentid;
	}
	public void setCurrentid(int currentid) {
		this.currentid = currentid;
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
	
	
	public void changePassword() {
		
	}
	
	public void changeAvatar() {
		
	}
	public void addEmail() {
		
	}
	
	public void createGroup() {
		
	}
	public ArrayList<User> userList() {
		ArrayList<User> userList = UserDAO.getListUser();
        return userList;
    } 
	public String delete(int id) {
        return UserDAO.delete(id);
    }

}