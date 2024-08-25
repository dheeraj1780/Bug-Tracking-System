package com.bts.signin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;



/**
 * this servlet is used to retreive the users details and put it in under user column priviledged to admin
 */
@WebServlet("/users")
public class UsersDataAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
//creating a nested hashmap for storing the user details
		Map<String, Map<String, Object>> users_details = new LinkedHashMap<>();	
		
// Get a database connection using the utility class
        Connection connection = null;
		try {
			connection = DatabaseUtility.getConnection();	
		} catch (SQLException e) {
			e.printStackTrace();
		}

//select all the rows from table users
        String query1 = "SELECT * FROM users";
        
        try (PreparedStatement ps = connection.prepareStatement(query1)) {
        	ResultSet rs = ps.executeQuery();
        	while(rs.next()) {
        		Map<String, Object> user = new LinkedHashMap<>();	
        		
        	//get username
        		String username = rs.getString("username");
        		
        	//put firstname into user hashmap
        		user.put("first_name", rs.getString("first_name"));
        		
        	//put lastname into user hashmap
        		user.put("last_name", rs.getString("last_name"));
        		
        	
        		
//select role of the corresponding username from the table role       		
        		String query2 = "SELECT r.role " +
                        "FROM users u " +
                        "JOIN role r ON u.role_id = r.role_id " +
                        "WHERE u.role_id=?";
        		PreparedStatement ps1 = connection.prepareStatement(query2);
        		ps1.setLong(1, rs.getInt("role_id"));
        		ResultSet role = ps1.executeQuery();
        		while(role.next()) {
        	//put role into user hashmap
        			user.put("role", role.getString("role"));
        		}
        		
        		
//select status of the corresponding username from the table role       		
        		String query3 = "SELECT s.status " +
                        "FROM users u " +
                        "JOIN status s ON u.status_id = s.status_id " +
                        "WHERE u.status_id=?";
        		PreparedStatement ps2 = connection.prepareStatement(query3);
        		ps2.setLong(1, rs.getInt("status_id"));
        		ResultSet status = ps2.executeQuery();
        		while(status.next()) {
        	//put status into user hashmap
        			user.put("status", status.getString("status"));
        		}

        		
        		users_details.put(username,user);
        	}
        	 
        	 request.setAttribute("usersData", users_details);

//	          Use RequestDispatcher to include the JSP and capture its output
	         RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
	         dispatcher.forward(request, response);
//			
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<p>Error: " + e.getMessage() + "</p>");
		}
	}
}


