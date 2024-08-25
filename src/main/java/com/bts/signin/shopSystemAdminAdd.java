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

@WebServlet("/shopsystemsadd")
public class shopSystemAdminAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");	
		
       	String software_ss = request.getParameter("software_ss");
       	String software_cms = request.getParameter("cms");
       	int cms_a= 1;
       	int ss_a= 1;
       	int subs_a= 1;
       	int nov_a= 1;
       	int php_a= 1;
       	//shopsystem
       	if(software_ss!="" && software_ss!=null) {
       		int ispresent = check(software_ss);
       		if(ispresent<0) {
       			insertsoftware(software_cms, software_ss);
       		}
       	}
	        // directing to shopSystems.jsp
       	RequestDispatcher dispatcher = request.getRequestDispatcher("/shopsystems");
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
		
		String query = "SELECT shopsystem_id FROM shopsystem WHERE shopsystem = '"+software_name+"'";
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
	@SuppressWarnings("resource")
	private void insertsoftware(String software_cms, String software_ss) {
	     // Get a database connection using the utility class
       Connection connection = null;
		try {
			connection = DatabaseUtility.getConnection();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PreparedStatement pstmt=null;
	    ResultSet rs = null;
	    int cms_id = 0;
	    int ss_id = 0;
	    String shortf = String.valueOf(software_ss.charAt(0))+String.valueOf(software_ss.charAt(software_ss.length() - 1));
	    String insertSoftwareQuery = "INSERT INTO shopsystem (shopsystem, code) VALUES ('"+software_ss+"','"+shortf+"')";
		try {
			pstmt = connection.prepareStatement(insertSoftwareQuery, Statement.RETURN_GENERATED_KEYS);

            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        ss_id = rs.getInt(1);
                    } 
            }
			if (software_cms!=null || software_cms!="") {
				String id = "SELECT cms_id FROM cms WHERE cms='"+software_cms+"'";
				pstmt = connection.prepareStatement(id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					cms_id = rs.getInt("cms_id");
				}
				String insertlink = "INSERT INTO cms_ss_link (cms_id,shopsystem_id) VALUES ('"+cms_id+"','"+ss_id+"')";
				pstmt = connection.prepareStatement(insertlink);
				pstmt.executeUpdate();
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
	}	

}
