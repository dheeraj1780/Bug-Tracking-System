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
import java.util.ArrayList;
import java.util.List;



/*
 * this Servlet handles generates the name of the report
 * and inserts optional parameters and novalnet version
 * db tables used
 * insertion - reports, novalnet, 
 * selection - shopsystem, cms
 * 
 * */



@WebServlet("/report")
public class reportsGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Obtain the RequestDispatcher object
        RequestDispatcher dispatcher = request.getRequestDispatcher("/reports");
        dispatcher.forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ss = request.getParameter("shop-system");
		String ss_v  = request.getParameter("shop-system-version");
		String cms = request.getParameter("cms");
		String cms_v = request.getParameter("cms-version");
		String nov_v = request.getParameter("novalnet-version");
		String opt1 = request.getParameter("optional-field-1");
		String opt1_v = request.getParameter("optional-value-1");
		String opt2 = request.getParameter("optional-field-2");
		String opt2_v = request.getParameter("optional-value-2");
		String opt3 = request.getParameter("optional-field-3");
		String opt3_v = request.getParameter("optional-value-3");
		String opt4 = request.getParameter("optional-field-4");
		String opt4_v = request.getParameter("optional-value-4");
		String opt5 = request.getParameter("optional-field-5");
		String opt5_v = request.getParameter("optional-value-5");
		String opt6 = request.getParameter("optional-field-6");
		String opt6_v = request.getParameter("optional-value-6");
		String opt7 = request.getParameter("optional-field-7");
		String opt7_v = request.getParameter("optional-value-7");
		System.out.println(cms+" cc "+cms_v+" nn "+ nov_v + opt1 + opt1_v + opt2 + opt2_v+"sss"+opt3+"dsds"+opt3_v);
		
	    CodeInfo shopSystemInfo = getcode("shopsystem", ss);
	    CodeInfo cmsInfo = getcode("cms", cms);
		
		List<String> names = new ArrayList<>();
        names.add(shopSystemInfo.getCode());
        names.add(cmsInfo.getCode());
        names.add("N");
        names.add(opt1);
        names.add(opt2);
        names.add(opt3);
        names.add(opt4);
        names.add(opt5);
        names.add(opt6);
        names.add(opt7);

        List<String> versions = new ArrayList<>();
        versions.add(ss_v);
        versions.add(cms_v);
        versions.add(nov_v);
        versions.add(opt1_v);
        versions.add(opt2_v);
        versions.add(opt3_v);
        versions.add(opt4_v);
        versions.add(opt5_v);
        versions.add(opt6_v);
        versions.add(opt7_v);
        
        StringBuilder resultBuilder = new StringBuilder();
        boolean firstPair = true;

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            String version = versions.get(i);

            if (name!=null && !name.isEmpty()) {
                if (firstPair) {
                    resultBuilder.append(name);
                    if (!version.isEmpty()) {
                        resultBuilder.append("_").append(version);
                    }
                    firstPair = false;
                } else {
                    resultBuilder.append("-").append(name);
                    if (!version.isEmpty()) {
                        resultBuilder.append("_").append(version);
                    }
                }
            }
        }

        String result = resultBuilder.toString();
        //invoking session for user_id to file report
		HttpSession session = request.getSession();
		int tester = (int) session.getAttribute("user_id");
		
        int[] ids = new int[11];
        System.out.println(insertReport(result)+"novalnet"+insertNovalnet(nov_v)+"shopsystem"+shopSystemInfo.getId()+"cms"+cmsInfo.getId()+"options"+insertOptionalParam(opt1,opt1_v));
        ids[0] = insertReport(result);
        ids[1]= insertNovalnet(nov_v);
        ids[2]= shopSystemInfo.getId();
        ids[3]= cmsInfo.getId();
        ids[4]= insertOptionalParam(opt1,opt1_v);
        ids[5]= insertOptionalParam(opt2,opt2_v);
        ids[6]= insertOptionalParam(opt3,opt3_v);
        ids[7]= insertOptionalParam(opt4,opt4_v);
        ids[8]= insertOptionalParam(opt5,opt5_v);
        ids[9]= insertOptionalParam(opt6,opt6_v);
        ids[10]= insertOptionalParam(opt7,opt7_v);
		
		insertReportDetails(ids);
		
		doGet(request,response);
	}
	
	private CodeInfo getcode(String table, String name) {
	    int id = 0;
	    String code = "";
	    Connection connection = null;

	    try {
	        connection = DatabaseUtility.getConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String query1 = "SELECT "+table+"_id, code FROM " + table + " WHERE " + table + " = ?";

	    try {
	        pstmt = connection.prepareStatement(query1);
	        pstmt.setString(1, name);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            id = rs.getInt(table+"_id");
	            code = rs.getString("code");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources in finally block to ensure they are always closed
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return new CodeInfo(id, code);
	}

	private int insertReport(String a) {
	    Connection connection = null;
	    int reportId = -1; // Default value in case of failure
	    try {
	        connection = DatabaseUtility.getConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String query1 = "INSERT INTO reports(report_name, status_id) VALUES (?, 1)";

	    try {
	        pstmt = connection.prepareStatement(query1, PreparedStatement.RETURN_GENERATED_KEYS);
	        pstmt.setString(1, a);
	        pstmt.executeUpdate();

	        // Get the generated keys
	        rs = pstmt.getGeneratedKeys();
	        if (rs.next()) {
	            reportId = rs.getInt(1); // Assuming the ID is the first column in the result set
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources in finally block to ensure they are always closed
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return reportId;
			}
	
	private int insertOptionalParam(String a, String b) {
	    int optionalId = 0; // Default value in case of failure

	    if (a != null && !a.isEmpty() && b != null && !b.isEmpty()) {
	        Connection connection = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            connection = DatabaseUtility.getConnection();

	            // Check if the record already exists
	            String query1 = "SELECT id FROM optional_software WHERE software = ? AND s_version = ?";
	            pstmt = connection.prepareStatement(query1);
	            pstmt.setString(1, a);
	            pstmt.setString(2, b);
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                // Record exists, return the existing ID
	                optionalId = rs.getInt("id");
	            } else {
	                // If the record does not exist, insert it and get the generated ID
	                rs.close();
	                pstmt.close();

	                String query2 = "INSERT INTO optional_software (software, s_version) VALUES (?, ?)";
	                pstmt = connection.prepareStatement(query2, PreparedStatement.RETURN_GENERATED_KEYS);
	                pstmt.setString(1, a);
	                pstmt.setString(2, b);
	                pstmt.executeUpdate();

	                rs = pstmt.getGeneratedKeys();
	                if (rs.next()) {
	                    optionalId = rs.getInt(1); // Assuming the ID is the first column in the result set
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
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

	    return optionalId;
	}
	
	@SuppressWarnings("resource")
	private int insertNovalnet(String version) {
	    Connection connection = null;
	    int novalnetId = 0; // Default value in case of failure

	    try {
	        connection = DatabaseUtility.getConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    

	    try {
	    	String query1 = "SELECT id FROM novalnet WHERE version = ?";
            pstmt = connection.prepareStatement(query1);
            pstmt.setString(1, version);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Record exists, return the existing ID
            	novalnetId = rs.getInt("id");
            } 
            else {
            	
            	String query2 = "INSERT INTO novalnet(version) VALUES (?)";
            	pstmt = connection.prepareStatement(query2, PreparedStatement.RETURN_GENERATED_KEYS);
            	pstmt.setString(1, version);
            	pstmt.executeUpdate();
            	
            	// Get the generated keys
            	rs = pstmt.getGeneratedKeys();
            	if (rs.next()) {
            		novalnetId = rs.getInt(1); // Assuming the ID is the first column in the result set
            	}
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources in finally block to ensure they are always closed
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return novalnetId;
	}

	private void insertReportDetails(int[] ids) {
	    if (ids == null || ids.length != 11) {
	        throw new IllegalArgumentException("The ids array must contain exactly 11 elements.");
	    }

	    Connection connection = null;
	    PreparedStatement pstmt = null;

	    try {
	        connection = DatabaseUtility.getConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    String query = "INSERT INTO report_details(report_id, novalnet_id, shopsystem_id, cms_id, option1_id, option2_id, option3_id, option4_id, option5_id, option6_id, option7_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        pstmt = connection.prepareStatement(query);
	        for (int i = 0; i < ids.length; i++) {
	        	if(ids[i]!=0) {
	        		pstmt.setInt(i + 1, ids[i]);
	        	}
	        	else {
	        		pstmt.setString(i + 1, null);
	        	}
	        }
	        pstmt.executeLargeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources in finally block to ensure they are always closed
	        try {
	            if (pstmt != null) pstmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	public class CodeInfo {
	    private int id;
	    private String code;

	    public CodeInfo(int id, String code) {
	        this.id = id;
	        this.code = code;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getCode() {
	        return code;
	    }
	}

}
