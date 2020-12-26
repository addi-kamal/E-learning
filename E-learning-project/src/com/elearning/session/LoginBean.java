package com.elearning.session;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class LoginBean {
	private String password;
	private String email;
	
	public String doLogin() {
		if(email.equals("admin")&&password.equals("password")) {
			HttpSession hs = Util.getSession();
			hs.setAttribute("email", email);
			return "Etudiant/MyApp.xhtml";
		} else {
			FacesMessage fm = new FacesMessage("Login Error", "ERROR MSG");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, fm);
			return "Etudiant/SignIn.xhtml";
		}
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
