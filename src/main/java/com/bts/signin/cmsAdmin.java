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


@WebServlet("/cms")
public class cmsAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	doGet(request, response);
    }  
    

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
		ArrayList<String> cms_list = new ArrayList<>();
		String query1 = "select cms from cms";
		try {
			pstmt = connection.prepareStatement(query1);
			rs = pstmt.executeQuery();
			while (rs.next()){
				cms_list.add(rs.getString("cms"));
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
		request.setAttribute("cms_list",cms_list);
     	RequestDispatcher dispatcher = request.getRequestDispatcher("cmsAdmin.jsp");
        dispatcher.forward(request, response);
		
		} 
	}
	
