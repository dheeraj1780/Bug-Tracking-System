package com.bts.signin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet("/blockedHtmlMail")
public class blockedHtmlMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession sess = request.getSession(false);
		  String username = (String) sess.getAttribute("username");
		  // Recipient's email ID needs to be mentioned.
	      String to = username;//receiver mail
	 
	      // Sender's email ID needs to be mentioned
	      String from = "test06022004@gmail.com";//server mail
	      
	      // Sender's password
	      String password = "njtt ufqr zvpe vkai";
	 
	      // Assuming you are sending email from localhost
	      String host = "smtp.gmail.com";
	 
	      // Get system properties
	      Properties properties = System.getProperties();
	 
	      // Setup mail server
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", "587");
	      
	        // creates a new session with an authenticator
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from, password);
	            }
	        };
	 
	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties, auth);
	      
	      // Set response content type
	      response.setContentType("text/html");
	      //PrintWriter out = response.getWriter();

	      try {
	         
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	         
	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));
	         
	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         // Set Subject: header field
	         message.setSubject("You have been approved");
	         
	         response.setContentType("text/html;charset=UTF-8");
	         

	         
	      // Create a StringWriter to capture JSP output
	         StringWriter stringWriter = new StringWriter();
	         PrintWriter writer = new PrintWriter(stringWriter);

	         // Use RequestDispatcher to include the JSP and capture its output
	         RequestDispatcher dispatcher = request.getRequestDispatcher("blockedMail.jsp");
	         dispatcher.include(request, new HttpServletResponseWrapper(response) {
	             public PrintWriter getWriter() {
	                 return writer;
	             }
	         });

	         // Get the HTML content as a string
	         String htmlContent = stringWriter.toString();
	         
	         
	         
	         // Send the actual HTML message, as big as you like
	         message.setContent(htmlContent, "text/html" );
	         
	         // Send message
	         Transport.send(message);
	         
	         sess.invalidate();
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}


}
