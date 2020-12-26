/**
 * 
 */
package com.elearning.common;

import  java.util.Hashtable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import  javax.naming.*;
import  javax.naming.directory.*;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

public class Email {
	
	public static int lookup(String hostName) throws NamingException {
		 Hashtable env = new Hashtable();
		 env.put("java.naming.factory.initial","com.sun.jndi.dns.DnsContextFactory");
		 DirContext ictx = new InitialDirContext( env );
		 Attributes attrs = ictx.getAttributes(hostName, new String[] {"MX"});
		 Attribute attr = attrs.get( "MX" );
		 if( attr == null ) return( 0 );
		 return( attr.size() );
	}
	
	public static void SendVerifCode(String email, HttpSession httpsession) {
		int length = 10;
	    boolean useLetters = false;
	    boolean useNumbers = true;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	    
	    httpsession.setAttribute("codeVerif", generatedString);

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
	    	  
	    	  //String content = "<h1>Helllooo everyone</h1>";
	    	  
	    	  //message.setContent(content, "text/html"); 
	    	  
	    	  message.setFrom(new InternetAddress(username));
	    	  
	    	  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
	    	  
	    	  // Set Subject
	    	  message.setSubject("Vérification d'Email - Plateorme E-Learning");
	    	  
	    	  
	    	  // Put the content of your message
	    	  
	    	  message.setText("Code de verification est: "+generatedString);

	    	 // Send message
	    	  Transport.send(message);
	    	  
	    	  System.out.println("Le message etait envoye....");
	    	  
	      } catch(MessagingException e) {
	    	  throw new RuntimeException(e);
	      }
	    
	}
	
}
