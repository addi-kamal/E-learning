package com.elearning.servlets;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.elearning.common.Email;
import com.elearning.db.Connexion;

/**
 * Servlet implementation class VerifyEmail
 */
@WebServlet("/verify-email")
public class VerifyEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public VerifyEmail() {
        super();}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String new_email = request.getParameter("new-email");
		
		
		//Verifier si le domaine de l'email est valide
		String domain = new_email.substring(new_email.indexOf('@')+1);
		try {
			if(Email.lookup(domain)!=0) {
				
				Connection connection = new Connexion().ConnectToDB();
				HttpSession session = request.getSession();
				String sql = "UPDATE users SET "
						+ "isVerified = '0',"
						+ "email = ?"
						+ "WHERE id = ?";
				
				try {
					PreparedStatement stm = connection.prepareStatement(sql);
					stm.setString(1, new_email);
					stm.setInt(2, (int)session.getAttribute("id"));
					
					stm.execute();
					System.out.println("Email updated!!");
					
					//On change la valeur d'email actuel d'user et la var isVerified
					session.setAttribute("email", new_email);
					session.setAttribute("isVerified", "0");
					
					//On redirige l'user a la page de verification d'email
					response.sendRedirect("code-verif");
					
				} catch (SQLException e) {
					System.out.println("Email not updated!!");
					//response.setContentType("text/html");
					e.printStackTrace();
				}
				
				
				
				
				
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
		
	}

}
