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
import java.sql.Statement;

@WebServlet("/cmsadd")
public class cmsAdminAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");	
		
       	String software_cms = request.getParameter("software_cms");
       	
       	//cms
       	if(software_cms!="" && software_cms!=null) {
       		int ispresent = check(software_cms);
       		if(ispresent<0) {
       			insertsoftware(software_cms);
       		}
       	}

	        // directing to shopSystems.jsp
       	RequestDispatcher dispatcher = request.getRequestDispatcher("/cms");
        dispatcher.forward(request, response);
//			
		} 
	// checking data method
	private int check(String software_name) {
	     // Get a database connection using the utility class
       Connection connection = null;
		try {
			connection = DatabaseUtility.getConnection();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String query = "SELECT cms_id FROM cms WHERE cms = '"+software_name+"'";
       PreparedStatement pstmt=null;
       int lastIdS = -1;
	    ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			rs = pstmt.executeQuery();
           if (rs.next()) {
           	lastIdS = rs.getInt(1);
               }  
           }
		catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return lastIdS;
	}
	
	// insert software method
	private void insertsoftware(String software_name) {
	     // Get a database connection using the utility class
       Connection connection = null;
		try {
			connection = DatabaseUtility.getConnection();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PreparedStatement pstmt=null;
	    ResultSet rs = null;
	    String shortf = String.valueOf(software_name.charAt(0))+String.valueOf(software_name.charAt(software_name.length() - 1));
		String insertSoftwareQuery = "INSERT INTO cms (cms, code) VALUES ('"+software_name+"','"+shortf+"')";
		try {
			pstmt = connection.prepareStatement(insertSoftwareQuery, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
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
	}	

}
