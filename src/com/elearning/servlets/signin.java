package com.elearning.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.elearning.beans.User;
//import com.elearning.db_javaee.UserDAO;

import com.elearning.users.User;
import com.elearning.db.Connexion;
import com.elearning.common.Sys;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class signin
 */
@WebServlet("/login")
public class signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signin() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String email = request.getParameter("email");
        String password = request.getParameter("password");
  	
    	try {
    		User user = new Sys().checkLogin(email, password);
			if(user!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("utilisateur", user);
				session.setAttribute("id", user.getCurrentid());
				session.setAttribute("user_type", user.getType());
				session.setAttribute("url_avatar",user.getUrlavatar());
				session.setAttribute("isVerified", user.getIsVerified());
				session.setAttribute("email", user.getEmail());
				
				String nom = user.getNom().substring(0,1).toUpperCase() + user.getNom().substring(1);
				String prenom = user.getPrenom().substring(0,1).toUpperCase() + user.getPrenom().substring(1);
				
				session.setAttribute("nom", nom);
				session.setAttribute("prenom", prenom);
				session.setAttribute("loggedIn", true);
				
				
				response.sendRedirect("profil");
			}else {
				System.out.print("NULL");
				response.sendRedirect("error.xhtml");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
}
