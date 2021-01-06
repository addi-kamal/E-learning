package com.elearning.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.beans.User;

/**
 * Servlet Filter implementation class Adminregister
 */
@WebFilter("/Adminregister")
public class Adminregister implements Filter {

    /**
     * Default constructor. 
     */
    public Adminregister() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
        //System.out.println("No one is authorized unless you're the admin");
        // pass the request along the filter chain
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        HttpSession session = request.getSession();
        User sessUser = (User) session.getAttribute("utilisateur");
        if ( session.getAttribute("utilisateur") == null || session.getAttribute("user_type").equals("P") || session.getAttribute("user_type").equals("S")) {
            /* Redirection vers la page publique */
            System.out.println("No one is authorized unless you're registred as Admin");
            request.getRequestDispatcher("/login").forward( request, response );
        }else {
            /* Affichage de la page restreinte */
            chain.doFilter( request, response );
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}