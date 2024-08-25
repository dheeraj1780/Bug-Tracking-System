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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/reports")
public class ReportsTester extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	doGet(request, response);
    }  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("reportRecords",reportRecords());
		response.setContentType("text/html");
		RequestDispatcher dispatcher = request.getRequestDispatcher("reportsTester.jsp");
        dispatcher.forward(request, response);
	}
	
	private Map<Integer,String[]> reportRecords() {
		// Get a database connection using the utility class
			Connection connection = null;
			try {
				connection = DatabaseUtility.getConnection();	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Map<Integer, String[]> records = new HashMap<>();
			
			int id = 0;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			String query1 = "select r.report_name, s.status_name from reports r natural join report_status s";
			try {
				pstmt = connection.prepareStatement(query1);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String[] record_pair = new String[2];
					record_pair[0] = rs.getString("report_name");
					record_pair[1] = rs.getString("status_name");
					++id;
					records.put(id, record_pair);
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
			return records;
	}
	
	@SuppressWarnings({ "resource"})
	private String pairs(int versionid, String software) {
		// Get a database connection using the utility class
		Connection connection = null;
		try {
			connection = DatabaseUtility.getConnection();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String first = "";
		String second = "";
		// setting up query
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String query1 = "select "+software+"_id, "+software+" from "+software+"_ver where ver_id="+versionid+"";
		try {
			
			int id = 0;
			pstmt = connection.prepareStatement(query1);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				id= rs.getInt(software+"_id");
				first = rs.getString(software);
				if (first==null){
						first="";
				}
				}
			
			String query2 = "select "+software+" from "+software+" where "+software+"_id = "+id+"";
			pstmt = connection.prepareStatement(query2);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				second = rs.getString(software);
				if (second==null){
					second="";
			}
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
		return second+"-"+first;
	}
}
