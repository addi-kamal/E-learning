package com.elearning.servlets;

import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.db.Connexion;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/change-password")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Il faut verifier que le nouveau mot de passe et le retape sont egaux
		//Il faut faire la validation des donnees dans le client side
		
		String new_pass = request.getParameter("new-pass");
		String r_new_pass = request.getParameter("r-new-pass");
		String old_pass = request.getParameter("old-pass");
		
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		if(!new_pass.equals(r_new_pass)) {
			pw.println("<html>"
					+ "<body>"
					+ "<p>Les 2 mots de passes sont differents!!</p>"
					+ "<p>'"
					+ new_pass
					+ "'</p>"
					+ "<p>'"
					+ r_new_pass
					+ "'</p>"
					+ "<p>'"
					+ old_pass
					+ "'</p>"
					+ "</body>"
					+ "</html>");  
		} else {
			Connection connection = new Connexion().ConnectToDB();
			HttpSession session = request.getSession();
			String sql = "SELECT password From users WHERE id = "+session.getAttribute("id");
			try {
				PreparedStatement stm = connection.prepareStatement(sql);
				ResultSet rs = stm.executeQuery();
				rs.next();
				String old_password = rs.getString("password");
				if(old_pass.equals(old_password)) {
					sql = "UPDATE users SET password = ? WHERE id="+session.getAttribute("id");
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setString(1, new_pass);
					statement.execute();
					pw.println("<html>"
							+ "<body>"
							+ "<p>Le mot de passe etait change</p>"
							+ "</body>"
							+ "</html>");  
				} else {
					pw.println("<html>"
							+ "<body>"
							+ "<p>Ancien Mot de passe est fauxx!!</p>"
							+ "</body>"
							+ "</html>");  
				}
				
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}

}
