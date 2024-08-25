
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html lang="en">
<head>
   	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/users.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <title>Bug_Tracker</title>
</head>

<body>
	
	
	<div class="container">

        <div class="item1">
        <%@ include file="index.jsp" %>
        </div>

        <div class="item2">
            <div class="filter">
                <a><button class="search-filter">searchfilter</button></a>
            </div>
        </div>

        <div class="item3"></div>

        <div class="item4">
            <table class="table">
                <thead>
                  <tr>
                    <th scope="col">Username</th>
                    <th scope="col">FirstName</th>
                    <th scope="col">LastName</th>
                    <th scope="col">Role</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                  </tr>
                </thead>
                <tbody>
                
                	<%-- Retrieve user details from the request attribute --%>
    				<% Map<String, Map<String, Object>> usersData = (Map<String, Map<String, Object>>) request.getAttribute("usersData"); %>
                	
				    <%-- Iterate over the user details and display them --%>
				    <% if (usersData != null) { %>
				        <% Set<Map.Entry<String, Map<String, Object>>> entrySet = usersData.entrySet(); %>
				        <% Iterator<Map.Entry<String, Map<String, Object>>> iterator = entrySet.iterator(); %>
				        
				            <% while (iterator.hasNext()) { %>
				                <% Map.Entry<String, Map<String, Object>> entry = iterator.next(); %>
				                <tr>
				                <td><%= entry.getKey() %></td>
				                    <% Map<String, Object> userAttributes = entry.getValue(); %>
				                    <% Set<Map.Entry<String, Object>> attributeSet = userAttributes.entrySet(); %>
				                    <% Iterator<Map.Entry<String, Object>> attributeIterator = attributeSet.iterator(); %>
				                    <% while (attributeIterator.hasNext()) { %>
				                        <% Map.Entry<String, Object> attributeEntry = attributeIterator.next(); %>
				                        <td><%= attributeEntry.getValue() %></td>
				                    <% } %>
				                    
				                    <%-- redirecting to the userview.java to see the details of individual user --%>
						        	<td><a href="<%= request.getContextPath() %>/userDetail?username=<%= entry.getKey()%>"><button id="view_btn">View</button></a></td>
				                </tr>
				            <% } %>
				    <% } else { %>
				        <tr><td colspan=7>No user details available.</td></tr>
				    <% } %>

                </tbody>
            </table>
        </div>
    </div>
    <script src="D:/Bug_Tracking/src/main/webapp/WEB-INF/lib/bootstrap/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>