package com.bts.signin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/addReports")
public class reportAddTester extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get a database connection using the utility class
		Connection connection = null;
		try {
			connection = DatabaseUtility.getConnection();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// setting up query
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		ArrayList<String> shopsystem_list = new ArrayList<>();
		String query1 = "select shopsystem from shopsystem;";
		try {
			pstmt = connection.prepareStatement(query1);
			rs = pstmt.executeQuery();
			while (rs.next()){
				shopsystem_list.add(rs.getString("shopsystem"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        // Close resources in finally block to ensure they are always closed
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		response.setContentType("text/html");
		request.setAttribute("shopsystem_list",shopsystem_list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("reportAddTester.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		// Get the value of the parameter named "shopSystem"
	    String shopSystem = request.getParameter("ss");
		// Get a database connection using the utility class
		Connection connection = null;
		try {
			connection = DatabaseUtility.getConnection();	
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// setting up query
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String cms = "";
		String query1 = "select c.cms from cms c join cms_ss_link l on c.cms_id=l.cms_id right join shopsystem s on l.shopsystem_id=s.shopsystem_id where s.shopsystem='"+shopSystem+"';";
		try {
		pstmt = connection.prepareStatement(query1);
		rs = pstmt.executeQuery();
		while (rs.next()){
			cms=rs.getString("c.cms");
		}
		System.out.println(cms);
		if (cms==null) {
			cms="-";
		}
		}
		catch (SQLException e) {
		e.printStackTrace();
		}finally {
		// Close resources in finally block to ensure they are always closed
		try {
		    if (rs != null) rs.close();
		    if (pstmt != null) pstmt.close();
		    if (connection != null) connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		}
		
		response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(cms);
	    
	}

}
