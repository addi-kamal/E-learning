package com.elearning.database;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.elearning.beans.User;

@ManagedBean
@SessionScoped
public class AddStudent {
	private User user = new User();
	//private ConnectJDBC connectJDBC = new ConnectJDBC();
	public User getUser() {
		return user;
	}

	public void setStudent(User user) {
		this.user = user;
	}
	/*public String saveStudent() {
		// enregistrer les etudiants au base de donn�es
		//ConnectJDBC.insertStudent(user);
		
		return "/E-learning-project/signup";
	}*/
	
	
}
