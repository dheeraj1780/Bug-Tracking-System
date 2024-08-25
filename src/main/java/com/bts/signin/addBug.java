package com.bts.signin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/bugs")
@jakarta.servlet.annotation.MultipartConfig
public class addBug extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "uploads";
	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String reportName = request.getParameter("report");

	        if (reportName != null && !reportName.isEmpty()) {
	            populateReportDetails(request, reportName);
	            populateDevelopersList(request);
	            int reportId = getReportId(reportName);
	            List<Map<String, String>> bugDetailsList = getBugDetails(reportId);
	            request.setAttribute("bugDetailsList", bugDetailsList);
	        } else {
	            // Handle case where report name is not provided
	            request.setAttribute("bugDetailsList", new ArrayList<>());
	        }

	        RequestDispatcher dispatcher = request.getRequestDispatcher("bugDetails.jsp");
	        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String report_name = request.getParameter("report");
		        String name = request.getParameter("bug_name");
		        String description = request.getParameter("description");
		        String testerfeedback = request.getParameter("testerfeedback");
		        String severity = request.getParameter("bug_severity");
		        String developerAssigned = request.getParameter("d_name");
		        String fUrl = request.getParameter("f_url");
		        String bUrl = request.getParameter("b_url");
		        
		        // Insert bug and get the generated ID
		        int bug_id = insertBug(name);
		        if (bug_id != -1) {
		            // Insert bug details with the generated bug_id
		            insertBugDetails(bug_id, name, description, testerfeedback, severity, developerAssigned, fUrl, bUrl);

		            // Insert into bug_report_link
		            int report_id = getReportId(report_name);
		            if (report_id != -1) {
		                bug_report_link(bug_id, report_id);
		            }
		        
		        
		        List<Part> fileParts = (List<Part>) request.getParts();
		        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		        File uploadDir = new File(uploadPath);
		        if (!uploadDir.exists()) {
		            uploadDir.mkdir();
		        }

		        for (Part filePart : fileParts) {
		            if (filePart.getName().equals("files")) {
		                String fileName = getFileName(filePart);
		                int image_id = -1;
		                if (fileName != null && !fileName.isEmpty()) {
		                    File file = new File(uploadPath + File.separator + fileName);
		                    filePart.write(file.getAbsolutePath());
		                    image_id = insertImageAndGetId(file.getAbsolutePath());
		                }
		                if(image_id!=-1) {
		                	insertImageBugLink(image_id, bug_id);
			            }
		            }
		        }}

		        doGet(request,response);
		    }
			
	
	private int insertBug(String name) {
	    Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
	    int generatedId = -1;

	    try {
	        connection = DatabaseUtility.getConnection();

	        // Setting up the query
	        String query1 = "INSERT INTO bug (bug_name) VALUES (?)"; 

	        pstmt = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
	        pstmt.setString(1, name);
	        pstmt.executeUpdate();

	        // Retrieve the generated keys
	        rs = pstmt.getGeneratedKeys();
	        if (rs.next()) {
	            generatedId = rs.getInt(1); // Get the generated ID
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources in finally block to ensure they are always closed
	        try {
	            if (rs != null) rs.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return generatedId; // Return the generated ID
	}


	private void insertBugDetails(int bugId, String name, String description, String testerFeedback, String severity, String developerAssigned, String fUrl, String bUrl) {
	    // Get a database connection using the utility class
	    Connection connection = null;

	    try {
	        connection = DatabaseUtility.getConnection();    
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return;
	    }

	    // Setting up the query
	    PreparedStatement pstmt = null;
	    String query = "INSERT INTO bug_details (bug_id, bug_name, description, feedback, severity_id, developer_id, status_id, f_url, b_url) " +
	                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

	    try {
	        pstmt = connection.prepareStatement(query);
	        pstmt.setInt(1, bugId);
	        pstmt.setString(2, name);
	        pstmt.setString(3, description);
	        pstmt.setString(4, testerFeedback);
	        pstmt.setInt(5, Integer.parseInt(severity)); // Assuming severity is stored as an integer ID
	        pstmt.setInt(6, Integer.parseInt(developerAssigned)); // Assuming developer is stored as an integer ID
	        pstmt.setInt(7, 1); // Assuming status_id is set to 1 for new entries, modify as needed
	        pstmt.setString(8, fUrl);
	        pstmt.setString(9, bUrl);
	        pstmt.executeLargeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources in the finally block to ensure they are always closed
	        try {
	            if (pstmt != null) pstmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

    @SuppressWarnings("resource")
	private int insertImageAndGetId(String imagePath) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int imageId = -1;

        try {
            // Get a database connection using the utility class
            connection = DatabaseUtility.getConnection();

            // Check if the image path already exists
            String queryCheck = "SELECT id FROM images WHERE image_path = ?";
            pstmt = connection.prepareStatement(queryCheck);
            pstmt.setString(1, imagePath);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Image path exists, get the ID
                imageId = rs.getInt("id");
            } else {
                // Image path does not exist, insert it
                String queryInsert = "INSERT INTO images (image_path) VALUES (?)";
                pstmt = connection.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, imagePath);
                pstmt.executeLargeUpdate();

                // Get the generated ID of the inserted image
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    imageId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return imageId;
    }
    
    private void insertImageBugLink(int imageId, int bugId) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            // Get a database connection using the utility class
            connection = DatabaseUtility.getConnection();

            // Prepare and execute the INSERT statement
            String query = "INSERT INTO image_bug_link (image_id, bug_id) VALUES (?, ?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, imageId);
            pstmt.setInt(2, bugId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	private void bug_report_link(int bug_id, int report_id) {
	    Connection connection = null;
	    PreparedStatement pstmt = null;

	    try {
	        connection = DatabaseUtility.getConnection();

	        // SQL query to insert the bug_id and report_id into the bug_report_link table
	        String insertLinkQuery = "INSERT INTO bug_report_link (bug_id, report_id) VALUES (?, ?)";
	        pstmt = connection.prepareStatement(insertLinkQuery);
	        pstmt.setInt(1, bug_id);
	        pstmt.setInt(2, report_id);

	        // Execute the update
	        pstmt.executeUpdate();
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

	@SuppressWarnings({ "null", "resource" })
	private int getReportId(String name) {
			Connection connection = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    int reportId = -1;
		    try {
		        connection = DatabaseUtility.getConnection();
		
		        // Check if the image path already exists in the images table
		        String checkImageQuery = "SELECT report_id FROM reports WHERE report_name = ?";
		        pstmt = connection.prepareStatement(checkImageQuery);
		        pstmt.setString(1, name);
		        rs = pstmt.executeQuery();
		        if (rs.next()) {
		            // If the image path exists, retrieve the image ID
		            reportId = rs.getInt("report_id");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
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

    private void populateReportDetails(HttpServletRequest request, String reportName) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            connection = DatabaseUtility.getConnection();

            String query1 = "SELECT " +
                    "ss.shopsystem AS shopsystem_name, " +
                    "ss.code AS shopsystem_version, " +
                    "c.cms AS cms_name, " +
                    "c.code AS cms_version, " +
                    "n.version AS novalnet_version, " +
                    "os1.software AS optional1_name, " +
                    "os1.s_version AS optional1_version, " +
                    "os2.software AS optional2_name, " +
                    "os2.s_version AS optional2_version, " +
                    "os3.software AS optional3_name, " +
                    "os3.s_version AS optional3_version, " +
                    "os4.software AS optional4_name, " +
                    "os4.s_version AS optional4_version, " +
                    "os5.software AS optional5_name, " +
                    "os5.s_version AS optional5_version, " +
                    "os6.software AS optional6_name, " +
                    "os6.s_version AS optional6_version, " +
                    "os7.software AS optional7_name, " +
                    "os7.s_version AS optional7_version " +
                    "FROM " +
                    "reports r " +
                    "JOIN report_details rd ON r.report_id = rd.report_id " +
                    "LEFT JOIN shopsystem ss ON rd.shopsystem_id = ss.shopsystem_id " +
                    "LEFT JOIN cms c ON rd.cms_id = c.cms_id " +
                    "LEFT JOIN novalnet n ON rd.novalnet_id = n.id " +
                    "LEFT JOIN optional_software os1 ON rd.option1_id = os1.id " +
                    "LEFT JOIN optional_software os2 ON rd.option2_id = os2.id " +
                    "LEFT JOIN optional_software os3 ON rd.option3_id = os3.id " +
                    "LEFT JOIN optional_software os4 ON rd.option4_id = os4.id " +
                    "LEFT JOIN optional_software os5 ON rd.option5_id = os5.id " +
                    "LEFT JOIN optional_software os6 ON rd.option6_id = os6.id " +
                    "LEFT JOIN optional_software os7 ON rd.option7_id = os7.id " +
                    "WHERE r.report_name = ?";
            
            pstmt = connection.prepareStatement(query1);
            pstmt.setString(1, reportName);
            rs = pstmt.executeQuery();

            String[] shopsystem = new String[2];
            String[] cms = new String[2];
            String[] novOptional = new String[15];
            
            if (rs.next()) {
                shopsystem[0] = rs.getString("shopsystem_name");
                shopsystem[1] = rs.getString("shopsystem_version");
                cms[0] = rs.getString("cms_name");
                cms[1] = rs.getString("cms_version");
                novOptional[0] = rs.getString("novalnet_version");
                novOptional[1] = rs.getString("optional1_name");
                novOptional[2] = rs.getString("optional1_version");
                novOptional[3] = rs.getString("optional2_name");
                novOptional[4] = rs.getString("optional2_version");
                novOptional[5] = rs.getString("optional3_name");
                novOptional[6] = rs.getString("optional3_version");
                novOptional[7] = rs.getString("optional4_name");
                novOptional[8] = rs.getString("optional4_version");
                novOptional[9] = rs.getString("optional5_name");
                novOptional[10] = rs.getString("optional5_version");
                novOptional[11] = rs.getString("optional6_name");
                novOptional[12] = rs.getString("optional6_version");
                novOptional[13] = rs.getString("optional7_name");
                novOptional[14] = rs.getString("optional7_version");
                
                request.setAttribute("shopsystem", shopsystem);
                request.setAttribute("cms", cms);
                request.setAttribute("novOptional", novOptional);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
    private void populateDevelopersList(HttpServletRequest request) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseUtility.getConnection();
            String query2 = "select u.user_id, u.first_name, u.last_name from users u natural join role r where r.role='developer'";
            pstmt = connection.prepareStatement(query2);
            rs = pstmt.executeQuery();

            List<Map<Integer, String>> developersList = new ArrayList<>();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                Map<Integer, String> developer = new HashMap<>();
                developer.put(userId, fullName);
                developersList.add(developer);
            }
            request.setAttribute("developersList", developersList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	private String getFileName(Part part) {
		        for (String content : part.getHeader("content-disposition").split(";")) {
		            if (content.trim().startsWith("filename")) {
		                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
		            }
		        }
		        return null;
    }

	private List<Map<String, String>> getBugDetails(int reportId) {
		
	    List<Map<String, String>> bugDetailsList = new ArrayList<>();

	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        connection = DatabaseUtility.getConnection();
	        String query = "SELECT b.bug_name, bs.status " +
	        		"FROM bug b " +
	        		"JOIN bug_details bd ON b.id = bd.bug_id " +
	        		"JOIN bug_status bs ON bd.status_id = bs.id " +
	        		"JOIN bug_report_link brl ON b.id = brl.bug_id " +
	        		"WHERE brl.report_id = ?";
	        pstmt = connection.prepareStatement(query);
	        pstmt.setInt(1, reportId);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Map<String, String> bugDetails = new HashMap<>();
	            bugDetails.put("bug_name", rs.getString("bug_name"));
	            bugDetails.put("status", rs.getString("status"));
	            bugDetailsList.add(bugDetails);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return bugDetailsList;
	}
}

