package com.elearning.servlets;

import java.io.*;
import java.util.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

import com.elearning.db.Connexion;

/**
 * Cette servlet fait upload d'avatar dans le dossier /images/user-pics/ 
 * et change le chemin vers cette image dans la base de donnees 
 */
@WebServlet("/avatarupload")
public class AvatarUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AvatarUpload() {super();}
    
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 5242880;
    private int maxMemSize = 5242880;
    private File file ;

    public void init( ){
        // Le dossier ou on va enregistrer les images
        filePath = getServletContext().getRealPath("images/user-pics/"); 
        System.out.println(filePath);
     }
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 // Check that we have a file upload request
	      isMultipart = ServletFileUpload.isMultipartContent(request);
	      
	      if( !isMultipart ) {
	    	 response.setContentType("text/html");
		     PrintWriter out = response.getWriter();
	         out.println("<h3>"
	         		+ "Aucun fichier n'a ete charge"
	         		+ "</h3>");
	         return;
	      }
	      
	      System.out.println("There is file upload request");
	      
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      
	      
	      //La taille maximale qui va etre stocke dans la memoire
	      factory.setSizeThreshold(maxMemSize);
	   
	      // Le dossier ou seront stockes les fichiers > maxMemSize.
	      factory.setRepository(new File("/home/rahima/Desktop/user-pics"));

	      // Creer un nouveau file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	   
	      // la taille maximale d'image qu'on peut charger.
	      upload.setSizeMax(maxFileSize);

	          // Parse the request to get file items.
			try {
				List fileItems = upload.parseRequest(request);
				
				 // get the uploaded file
		        Iterator i = fileItems.iterator();
		          
		        if (i.hasNext()) {
		        	  System.out.println("There is an item upload");
		              FileItem fi = (FileItem)i.next();
		              if ( !fi.isFormField () ) {
		            	  
		                 String fileName = fi.getName();
		                 
		                 String name = "avatar-"+ (int)request.getSession().getAttribute("id") + fileName.substring(fileName.lastIndexOf("."));
		                 
		                 System.out.println(filePath+name);
		                 
		                 file = new File(filePath + name);
		                 fi.write(file) ;
		                 
		               /**
		                * Maintenant, il faut faire la mise a jour dans la base donnee
		                * Modifier la valeur du champs url_avatar pour cet utilisateur
		                * 
		                * **/
		                 
		                 Connection connection = new Connexion().ConnectToDB();
		                 String path_to_avatar = "images/user-pics/"+name;
		                 System.out.println(path_to_avatar);
		                 String sql = "UPDATE users SET url_avatar = ? WHERE id = ?";
		                 PreparedStatement stm = connection.prepareStatement(sql);
		                 
		                 stm.setString(1, path_to_avatar);
		                 stm.setInt(2, (int)request.getSession().getAttribute("id"));
		                 stm.execute();
		                 
		                 request.getSession().setAttribute("url_avatar", path_to_avatar);
		                 
		                 response.sendRedirect("profil");
		                 
		                 
		              }
		           } else {
		        	   System.out.println("There is no file upload!!!");
		           }
		          
		          
		          
		          
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	
	         
	          
	          
	      
		
		
		
		
	}

}
