package com.bts.signin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * this servlet make sures that after the user enters his details he gets redirected to a page and a html mail is sent to the admin
 */
@WebServlet("/register")
public class VerificationSender extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     
//		  retreiving input parameter
		  String firstname = request.getParameter("firstname");
		  String lastname = request.getParameter("lastname");
		  String email = request.getParameter("username");
		  String role = request.getParameter("role");	
		  
// Get a database connection using the utility class
	        Connection connection = null;
			try {
				connection = DatabaseUtility.getConnection();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
// getting roleid from the role table corresponding to the role selected
			String query1 = "SELECT role_id FROM role WHERE role=?";
	        try (PreparedStatement ps1 = connection.prepareStatement(query1)) {
	            
	        	ps1.setString(1, role);
	        	ResultSet rs = ps1.executeQuery();
	        	int role_id = 0;
	        	while(rs.next()) {	
	        		role_id = rs.getInt("role_id");
	        	}
	        	
// inserting values into users table       	
	        	String query2 = "INSERT INTO users (first_name, last_name, username, role_id) VALUES (?, ?, ?, ?)";
	        	PreparedStatement ps2 = connection.prepareStatement(query2);
	            ps2.setString(1, firstname);
	            ps2.setString(2, lastname);
	            ps2.setString(3, email);
	            ps2.setLong(4, role_id);

	            // Execute the insert query
	            long rowsAffected = ps2.executeLargeUpdate();

	            if (rowsAffected > 0) {
	                // Insert successful
	                response.getWriter().println("User data inserted successfully!");
	            } else {
	                // Insert failed
	                response.getWriter().println("Failed to insert user data.");
	            }} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  
		  
		  
		  
		  
//mail is to be sent to the user signed from the server mail		  
		  
		  
// Recipient's email ID needs to be mentioned.
	      String to = "jdeeran2004@gmail.com";//admin mail
	 
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


	      try {
	         
// Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	         
// Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));
	         
// Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
// Set Subject: header field
	         message.setSubject("APPROVAL WAITING...");
	         
	         response.setContentType("text/html;charset=UTF-8");
	         request.setAttribute("firstname", firstname);
	         request.setAttribute("lastname", lastname);
	         request.setAttribute("email", email);
	         request.setAttribute("role", role);
	         
// Create a StringWriter to capture JSP output
	         StringWriter stringWriter = new StringWriter();
	         PrintWriter writer = new PrintWriter(stringWriter);

// Use RequestDispatcher to include HtmlEmail.jsp and capture its output
	         RequestDispatcher dispatcher = request.getRequestDispatcher("HtmlEmail.jsp");
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
	         
	         
// redirecting to waiting_approval.jsp
	         response.setContentType("text/html");
	         RequestDispatcher dispatcher_page = request.getRequestDispatcher("waiting_approval.jsp");
	         dispatcher_page.forward(request, response);
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}

}
	
