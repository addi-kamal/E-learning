package com.elearning.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.db.Connexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;




@WebServlet(name = "delete-account", urlPatterns = { "/delete-account" })
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteAccount() {super();}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1 - logout
		HttpSession session = request.getSession();
		int id = (int)session.getAttribute("id");
		if(session.getAttribute("loggedIn")!=null) session.invalidate();
		
		
		
		//2- supprimer la ligne de cet utilisateur dans la bd
		
		Connection connection = new Connexion().ConnectToDB();
		
		String sql = "DELETE FROM users WHERE id = " + id;
		
		try {
			Statement stm = connection.createStatement();
			stm.execute(sql);
			
			//3- Rediriger l'utilisateur vers Home page
			
			response.sendRedirect("/index.xhmtl");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
