package com.elearning.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elearning.beans.Lesson;
import com.elearning.db.Connexion;

/**
 * Servlet implementation class AfficherLesson
 */
@WebServlet("/Professeur/Cours/AfficherLesson")
public class AfficherLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherLesson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("lessonid");
		Lesson l =new Lesson();
	        try {
	        	    Connection conn = new Connexion().ConnectToDB();	
	                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM lesson  WHERE lesson_id=?;");
	                pstmt.setString(1, id);
	                ResultSet rs= pstmt.executeQuery();
	                rs.next();
	                l.setId(rs.getString("lesson_id")); 
	                l.setTitre(rs.getString("titre"));  
	                l.setDateCreation(rs.getDate("dateCreation"));  
	                l.setCreatedBy(rs.getString("CreatedBy"));  
	                l.setCours_id(rs.getString("cours_id")); 
	                l.setText(rs.getString("text"));
	                conn.close();
	        } catch(Exception sqlException) {
	            sqlException.printStackTrace();
	        }
		String text=l.getText();
		request.setAttribute("text", text);
        response.setContentType("text/html"); 
        PrintWriter pw=response.getWriter();
        pw.println(text); 
		this.getServletContext().getRequestDispatcher("/Professeur/Cours/AfficherLesson.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
