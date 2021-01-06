package com.elearning.common;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.NamingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.activation.*;


public class SendEmail {
	 public static void main(String [] args) throws NamingException {  
		 
		 //String email = "largorahima@gmail.com";
		 
		 //System.out.print("gmail.com : "+Email.lookup(email.substring(email.indexOf('@')+1)));
		 
	      // email ID of Recipient.

	      String recipient = "largorahima@gmail.com"; 
	      
	      //check if the domain of the email exists 
	      if(Email.lookup(recipient.substring(recipient.indexOf('@')+1))!=0) {
	    	  // email ID of  Sender. 
		      String from = "tt0827843@gmail.com";
		      String username = "tt0827843";
		      String password = "Testtest12@";
		  
		      String host = "smtp.gmail.com";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true"); 
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", "587");
		       
		      Session session = Session.getInstance(props,
		    		  new javax.mail.Authenticator() {
		    		  protected PasswordAuthentication getPasswordAuthentication() {
		    		  return new PasswordAuthentication(username, password);
		    		  }
		    		  });
		      
		      try {
		    	  // Create a default MimeMessage object
		    	  Message message = new MimeMessage(session);
		    	  
		    	  String content = "<h1>Helllooo everyone</h1>";
		    	  
		    	  message.setContent(content, "text/html"); 
		    	  
		    	  message.setFrom(new InternetAddress(username));
		    	  
		    	  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
		    	  
		    	  // Set Subject
		    	  message.setSubject("Vérification d'Email - AjiTEQRA a babak");
		    	  
		    	  //generate a random number
		    	  
		    	  
		    	  // Put the content of your message
		    	  String str ="";
		    	  message.setText("Code de verification est: "+str);

		    	 // Send message
		    	  Transport.send(message);
		    	  
		    	  System.out.println("Sent message successfully....");
		    	  
		      } catch(MessagingException e) {
		    	  throw new RuntimeException(e);
		      }
	    	  
	      } else {
	    	  System.out.println("Le domaine n'existe pas!!");
	      }
	  
	     
	      

	   }
}