package com.bts.signin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/home")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//requesting input parameters of users from login.jsp
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		
// Get a database connection using the utility class
        Connection connection = null;
		try {
			connection = DatabaseUtility.getConnection();	
		} catch (SQLException e) {
			e.printStackTrace();
		}

//Check user data into the table
        String query1 = "SELECT u.user_id, u.last_name, r.role FROM users u JOIN credentials c ON u.user_id = c.user_id JOIN role r ON u.role_id = r.role_id WHERE u.username=? AND c.password=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query1)) {
        	
// Set parameters for the PreparedStatement
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            System.out.println(query1);
// Execute the insert query
            ResultSet rowsAffected = preparedStatement.executeQuery();
            
            if (rowsAffected.next()) {
            	
// Retrieve data using a while loop
            	String role= "";
            	int user=0;
                do {
                    role = rowsAffected.getString("role");
                    user = rowsAffected.getInt("user_id");
                    
                } while (rowsAffected.next());
            
// admin variable
                boolean admin = false;
            	
// create a session for user handling
            	HttpSession session = request.getSession();
            	
// session attibutes
            	session.setAttribute("username",email);
            	session.setAttribute("role", role);
            	session.setAttribute("user_id", user);
//admin validation
            	if(role.equals("admin")) {
            		admin = true;
            	}
            	session.setAttribute("isAdmin", admin);
                response.getWriter().println("Successfully loggedin!");
                
//redirecting to index.jsp(dashboard)
                RequestDispatcher dispatcher_page = request.getRequestDispatcher("index.jsp");
   	         	dispatcher_page.forward(request, response);
            } else {
            	
                // Insert failed
            	response.setContentType("text/html");
            	request.setAttribute("wrong", true);
            	
//including the error msg in the login page for wrong credentials
            	RequestDispatcher dispatcher_page = request.getRequestDispatcher("login.jsp");
   	         	dispatcher_page.include(request, response);
            }
            } catch (SQLException e) {
				e.printStackTrace();
			}
	}

}