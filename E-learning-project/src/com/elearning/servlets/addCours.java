package com.elearning.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.beans.Course;
import com.elearning.beans.User;
import com.elearning.database.CoursDAO;

/**
 * Servlet implementation class addCours
 */
@WebServlet("/Professeur/addCours")
public class addCours extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCours() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/Professeur/addCours .xhtml").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("utilisateur");
		String id = String.valueOf(user.getCurrentid());
		Course c = new Course();
		c.setCreatedby(id);
		c.setTitre(request.getParameter("titre"));
		c.setId_sujet(request.getParameter("id_sujet"));
		c.setId_niveau(request.getParameter("niveau"));
		c.setEstimated_time(request.getParameter("time"));
		c.setPrix(request.getParameter("prix"));
		c.setLanguage(request.getParameter("language"));
		c.setDesc(request.getParameter("description"));
		CoursDAO obj= new CoursDAO();
		obj.addCours(c, id);
		doGet(request, response);
	}

}