package com.elearning.beans;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.elearning.db_javaee.RessourceDAO;

@ManagedBean(name="ressource")
@ViewScoped
public class Ressource {
	private int ressource_id;
	private String nom;
	private String lesson;
	private Date dateCreation;
	private String createdBy;
	private int lesson_id;
	private String nomFile;
	private Part uploadedFile;
	private String folder = "C:\\files";
	//private String folder = "/WEB-INF";
	
	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	public int getRessource_id() {
		return ressource_id;
	}
	public void setRessource_id(int ressource_id) {
		this.ressource_id = ressource_id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public int getLesson_id() {
		return lesson_id;
	}
	public void setLesson_id(int lesson_id) {
		this.lesson_id = lesson_id;
	}
	public String getNomFile() {
		return nomFile;
	}
	public void setNomFile(String nomFile) {
		this.nomFile = nomFile;
	}

	public String saveRessource(String lesson,int idProf,Ressource r, String nom, String prenom) {
		try (InputStream input = uploadedFile.getInputStream()) {
			String fileName = uploadedFile.getSubmittedFileName();
			System.out.print(fileName);
	        Files.copy(input, new File(folder, fileName).toPath());
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
        return RessourceDAO.saveRessource(lesson,idProf,r,nom,prenom);
    }
	public ArrayList<Ressource> ressourceList(int id) {
		ArrayList<Ressource> ressourceList = RessourceDAO.getListRessource(id);
		System.out.println(ressourceList);
        return ressourceList;
    }
	
	public String download(String fileName) {
		System.out.println(fileName);
		File file = new File("C:\\files\\"+fileName);
       try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
    	    String contentType = externalContext.getMimeType(fileName); 
    	    int contentLength = (int) file.length();
            externalContext.responseReset();
            externalContext.setResponseContentType("contentType");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            
            FileInputStream inputStream = new FileInputStream(new File("C:\\files\\"+fileName));
            OutputStream outputStream = externalContext.getResponseOutputStream();

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            context.responseComplete();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
       return "/listRessource.xhtml?faces-redirect=true";
	
	}
	
}
