package com.bts.signin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/userDetail")
public class usersview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//get username parameter sent via url
		String username= request.getParameter("username");
		
// Get a database connection using the utility class
        Connection connection = null;
		try {
			connection = DatabaseUtility.getConnection();	
		} catch (SQLException e) {
			e.printStackTrace();
		}

// Select user data from the users table
        String query1 = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement ps1 = connection.prepareStatement(query1)) {
        	
        	ps1.setString(1, username);
        	ResultSet rs = ps1.executeQuery();
        	while(rs.next()) {	
        		
        //set attribute firstname
        		request.setAttribute("first_name", rs.getString("first_name"));
        		
        //set attribute lastname
        		request.setAttribute("last_name", rs.getString("last_name"));
        		
        //set attribute username
        		request.setAttribute("username",username);

        		
// Select role data from the role table
        		String query2 = "SELECT r.role " +
                        "FROM users u " +
                        "JOIN role r ON u.role_id = r.role_id " +
                        "WHERE u.role_id=?";
        		PreparedStatement ps2 = connection.prepareStatement(query2);
        		ps2.setLong(1, rs.getInt("role_id"));
        		ResultSet role = ps2.executeQuery();
        		while(role.next()) {
        			request.setAttribute("role", role.getString("role"));
        		}
        		
        		
// Select status data from the status table      		
        		String query3 = "SELECT s.status " +
                        "FROM users u " +
                        "JOIN status s ON u.status_id = s.status_id " +
                        "WHERE u.status_id=?";
        		PreparedStatement ps3 = connection.prepareStatement(query3);
        		ps3.setLong(1, rs.getInt("status_id"));
        		ResultSet status = ps3.executeQuery();
        		while(status.next()) {
        			request.setAttribute("status", status.getString("status"));
        		}

        	}
        } catch (SQLException e) {
			e.printStackTrace();
		}
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewuser.jsp");
        dispatcher.forward(request, response);
		response.getWriter().println("Username: "+ username);
	}

}
