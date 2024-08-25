package com.bts.signin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/user_approved")
public class user_approved extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
	// getting parameter from the url
		String status = request.getParameter("status");
        String username = request.getParameter("username");
        
        	
        
	// Get a database connection using the utility class
		Connection connection = null;
		try {
			connection = DatabaseUtility.getConnection();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	//empty userStatus string	
		int userStatus = 4;
		
        // Perform action based on the button clicked
		
//approved status consequences
        if (status.equals("approved")) {
            userStatus = 1;
            
            String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
            String numbers = "0123456789";
            String specialCharacters = "!@#$%^&*()-_=+";
            
            String allCharacters = uppercaseLetters + lowercaseLetters + numbers + specialCharacters;
            
            StringBuilder generatepswd = new StringBuilder();
            SecureRandom random = new SecureRandom();
            
            for (int i = 0; i < 9; i++) {
            	int index = random.nextInt(allCharacters.length());
            	generatepswd.append(allCharacters.charAt(index));
            }
            
            String pswd = generatepswd.toString();
            
            HttpSession sess = request.getSession();
            sess.setAttribute("pswd", pswd);
            sess.setAttribute("username", username);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/approvalHtmlMail");
            dispatcher.include(request, response);
            
      //selecting the userid of the corresponding username       
            String query1 = "SELECT user_id FROM users WHERE username = ? ";
            int user_id = 0;
            try (PreparedStatement ps1 = connection.prepareStatement(query1)) {
            	ps1.setString(1, username);
        		ResultSet userid = ps1.executeQuery();
        		while(userid.next()) {
        			user_id = userid.getInt("user_id");
        		}
            	} catch (SQLException e) {
    				e.printStackTrace();
    			}
      //inserting the passw0rd into the credentials table corresponding to the user_id  
            String query2 = "INSERT INTO credentials (user_id, password) VALUES (?, ?);";
            
            try (PreparedStatement ps2 = connection.prepareStatement(query2)) {
            	ps2.setInt(1, user_id);
            	ps2.setString(2, pswd);
                ps2.executeUpdate();
            	} catch (SQLException e) {
    				e.printStackTrace();
    			}
            
        } 

//disapproved status consequences
        else if (status.equals("disapproved")) {
            userStatus = 2;
      
       //deleting the row if dissaproved             
            String query3 = "DELETE FROM users WHERE (username = ?);";
            try (PreparedStatement ps = connection.prepareStatement(query3)) {
            	ps.setString(1, username);
                ps.executeUpdate();
            	} catch (SQLException e) {
    				e.printStackTrace();
    			}
            
        } 
        
//blocked status consequences
        else if (status.equals("blocked")) {
        	userStatus = 3;
        	
        	HttpSession sess = request.getSession();
        	sess.setAttribute("username", username);	
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/blockedHtmlMail");
            dispatcher.include(request, response);
        }
        
        

        // Update the status
        String query3 = "UPDATE users SET status_id = ? WHERE username = ?;";
        try (PreparedStatement ps = connection.prepareStatement(query3)) {
        	ps.setInt(1, userStatus);
        	ps.setString(2, username);
            ps.executeUpdate();
        	} catch (SQLException e) {
				e.printStackTrace();
			}

        // Redirect back to the JSP page
        response.sendRedirect(request.getContextPath() + "/users");
		
	}
}


