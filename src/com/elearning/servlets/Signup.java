package com.elearning.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.beans.User;
import com.elearning.database.ConnectJDBC;

@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Signup() {super();}
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Map<String, String[]> params = request.getParameterMap();
		
		User user = new User();
		//email, password, prenom, nom, telephone, ville, pays, niveau, dateNaissance, code_postal
		/*user.setEmail(params.get("email")[0]);
		user.setPassword(params.get("password")[0]);
		user.setNom(params.get("nom")[0]);
		user.setPrenom(params.get("prenom")[0]);
		user.setTelephone(params.get("tel")[0]);
		user.setPays(params.get("pays")[0]);
		user.setVille(params.get("ville")[0]);
		user.setNiveau(params.get("niveau")[0]);
		user.setCode_postal(params.get("code_postal")[0]);
		user.setDateNaissance(params.get("dateNaissance")[0]);*/
		
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setNom(request.getParameter("nom"));
		user.setPrenom(request.getParameter("prenom"));
		user.setTelephone(request.getParameter("tel"));
		user.setPays(request.getParameter("pays"));
		user.setVille(request.getParameter("ville"));
		user.setNiveau(request.getParameter("niveau"));
		user.setCode_postal(request.getParameter("code_postal"));
		user.setDateNaissance(request.getParameter("dateNaissance"));
		
		
		user = ConnectJDBC.insertStudent(user);
		
		if(user!=null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("utilisateur", user);
			session.setAttribute("id", user.getCurrentid());
			session.setAttribute("user_type", "S");
			session.setAttribute("url_avatar","images/user-pics/default-avatar.png");
			session.setAttribute("isVerified", "0");
			session.setAttribute("email", user.getEmail());
			
			String nom = user.getNom().substring(0,1).toUpperCase() + user.getNom().substring(1);
			String prenom = user.getPrenom().substring(0,1).toUpperCase() + user.getPrenom().substring(1);
			
			session.setAttribute("nom", nom);
			session.setAttribute("prenom", prenom);
			session.setAttribute("loggedIn", true);
			
			response.sendRedirect("profil");
		} else {
			response.sendRedirect("error.xhtml");
		}
		
		
		
	}

}
