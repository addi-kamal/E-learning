package com.elearning.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import com.elearning.users.User;


@WebServlet("/profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Profil() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loggedIn")!=null && session.getAttribute("loggedIn").equals(true)){ // the user is logged in 
			if(session.getAttribute("user_type").equals("S"))
				request.getRequestDispatcher("Etudiant/profil.xhtml").forward(request, response);
			
			else if(session.getAttribute("user_type").equals("A"))
				request.getRequestDispatcher("Admin/profil.xhtml").forward(request, response);
			
			else request.getRequestDispatcher("Professeur/profil.xhtml").forward(request, response);
		} else {
			System.out.println("Not LoggedIn");
			//On a besoin de creer des pages pour que un user qlq peut voir le profil d'un etudiant,prof mais non d'un admin 
			/**
			 * view the user's page
			 * **/
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
}
