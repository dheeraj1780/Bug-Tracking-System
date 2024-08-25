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
import java.util.HashMap;
import java.util.Map;


@WebServlet("/shopsystems")
public class shopSystemAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	doGet(request, response);
    }  
    

	@SuppressWarnings("resource")
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
		ArrayList<ArrayList<String[]>> table = new ArrayList<ArrayList<String[]>>();
		String query1 = "select c.cms, s.shopsystem from cms c join cms_ss_link l on c.cms_id=l.cms_id right join shopsystem s on l.shopsystem_id=s.shopsystem_id;";
		try {
			pstmt = connection.prepareStatement(query1);
			rs = pstmt.executeQuery();
			while (rs.next()){
				String cms_key = rs.getString("c.cms");
				String shopsystem_value = rs.getString("s.shopsystem");
				ArrayList<String[]> pair = new ArrayList<String[]>();
				if (cms_key!=null) {
					pair.add(new String[]{cms_key, shopsystem_value});
					table.add(pair);
				}
				else if(cms_key=="" || cms_key==null) {
					pair.add(new String[]{"-", shopsystem_value});
					table.add(pair);
				}
				
			}


			String query2 = "select cms from cms";
			pstmt = connection.prepareStatement(query2);
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
		request.setAttribute("table",table);
		request.setAttribute("cms_list",cms_list);
     	RequestDispatcher dispatcher = request.getRequestDispatcher("shopSystemsAdmin.jsp");
        dispatcher.forward(request, response);
		
		} 
	}
	


