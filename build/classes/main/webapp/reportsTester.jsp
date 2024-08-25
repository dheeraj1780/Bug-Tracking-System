<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map.Entry" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
   	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/reportsTester.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="/pracc/src/main/webapp/js/main.js"></script>
    <title>Bug_Tracker</title>
</head>

<body>
	
	
	<div class="container">

        <div class="item1">
        <%@ include file="index.jsp" %>
        </div>

        <div class="item4">
        	<div class="box" id="box1">
        		<a href="http://localhost:8081/pracc/addReports"><button id="add" type="submit" value="Submit">ADD REPORT</button></a>
        	</div>
            <table class="table">
                <thead>
                  <tr>
                    <th scope="col">Report Name</th>
                    <th scope="col">Status</th>
                    <th scope="col">View</th>
                  </tr>
                </thead>
				<tbody>
				<%Map<Integer, String[]> records = (Map<Integer, String[]>) request.getAttribute("reportRecords"); %>
        <% 
            // Iterate through the dataMap
            Set<Entry<Integer, String[]>> entrySet = records.entrySet();
            Iterator<Entry<Integer, String[]>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<Integer, String[]> entry = iterator.next();
                Integer key = entry.getKey();
                String[] values = entry.getValue();
        %>
            <tr>
                <td><%= values[0] %></td>
                <td><%= values[1] %></td>
                <td><a href="http://localhost:8081/pracc/bugs?report=<%=values[0]%>"><button>VIEW</button></a></td>>
            </tr>
        <% } %>
				</tbody>
            </table>
        </div>
    </div>
    <script src="D:/Bug_Tracking/src/main/webapp/WEB-INF/lib/bootstrap/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>