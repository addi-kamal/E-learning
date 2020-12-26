package com.elearning.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elearning.db.Connexion;

/**
 * Servlet implementation class IsCodeEqual
 */
@WebServlet("/iscodeequal")
public class IsCodeEqual extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public IsCodeEqual() {super();}
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code_verif_user = request.getParameter("code-verif-user");
		
		String code_sent = (String) request.getSession().getAttribute("codeVerif");
		
		if(code_verif_user.equals(code_sent)) {
			/**
			 * Changer la valeur de isVerified dans la bd et la var de session
			 * 
			 * **/
			request.getSession().setAttribute("isVerified", "1");
			
			Connection connection = new Connexion().ConnectToDB(); //cnx etablie 
			
			String sql = "UPDATE users SET isVerified = '1' WHERE id= " + (int) request.getSession().getAttribute("id") ;
			
			try {
				PreparedStatement stm = connection.prepareStatement(sql);
				stm.execute(sql);
				System.out.println("isVerified is changed");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			response.sendRedirect("settings");
		
		} else {
			String to_embed = "<script>"
					+ "alert('Le code que vous avez entre est faux!!')"
					+ "</script>";
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    out.println(to_embed);
			request.getRequestDispatcher("/Etudiant/codeverif.xhtml").include(request, response);
			
		}
		
		
		
	}

}
