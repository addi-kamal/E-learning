package com.elearning.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.common.Email;
import com.elearning.db.Connexion;

/**
 * Servlet implementation class CodeVerif
 */
@WebServlet("/code-verif")
public class CodeVerif extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CodeVerif() {super();}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String email = (String) request.getSession().getAttribute("email");
		
		//Envoyer l'email de verification, pas besoin de faire lookup car il faut l'ajouter a la phase de register
		
		Email.SendVerifCode(email, request.getSession());
		if(!response.isCommitted())
			request.getRequestDispatcher("/Etudiant/codeverif.xhtml").forward(request, response);
		else {
				System.out.println("Response already commited!!!");
		}
		
	
	
	}
}
