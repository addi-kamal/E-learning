package com.elearning.database;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.elearning.beans.Student;
@ManagedBean
@ViewScoped
public class AddStudent {
	private Student student = new Student();
	private ConnectJDBC connectJDBC = new ConnectJDBC();
	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	public String saveStudent() {
		// enregistrer les etudiants au base de données
		connectJDBC.insertStudent(student);
		return "SignIn";
	}
	
	
}
