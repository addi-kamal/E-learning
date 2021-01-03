package com.elearning.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.beans.User;
import com.elearning.database.ConnectJDBC;

/**
 * Servlet implementation class SignupAP
 */
@WebServlet("/SignupAP")
public class SignupAP extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SignupAP() {super();}
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/Admin/CreateAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Map<String, String[]> params = request.getParameterMap();
		
		User user = new User();
		
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
		user.setUser_type(request.getParameter("user_type"));
		user = ConnectJDBC.insertAdminProf(user);
		
		this.getServletContext().getRequestDispatcher("/Admin/CreateAccount.jsp").forward(request, response);
		
		
	}

}