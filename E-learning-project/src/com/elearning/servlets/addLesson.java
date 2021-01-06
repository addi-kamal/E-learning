package com.elearning.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.beans.Course;
import com.elearning.beans.Lesson;
import com.elearning.beans.User;
import com.elearning.database.LessonDAO;

/**
 * Servlet implementation class addLesson
 */
@WebServlet("/Professeur/Cours/addLesson")
public class addLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addLesson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/Professeur/Cours/AddLesson.xhtml").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("utilisateur");
		String idProf = String.valueOf(user.getCurrentid());
		String idCours = request.getParameter("Id");
		Lesson l = new Lesson();
		l.setCours_id(idCours);
		l.setCreatedBy(idProf);
		l.setText(request.getParameter("text"));
		l.setTitre(request.getParameter("titre"));
		LessonDAO.addLesson(l, idCours , idProf);
		doGet(request, response);
	}

}