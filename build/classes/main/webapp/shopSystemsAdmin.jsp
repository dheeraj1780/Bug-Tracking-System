<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
   	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/shopSystemsAdmin.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/main.js"></script>
    <title>Bug_Tracker</title>
</head>

<body>
	
	
	<div class="container">

        <div class="item1">
        <%@ include file="index.jsp" %>
        </div>
		<form action="shopsystemsadd" method="POST">
		    <div class="item2">
	        	
	        	<div class="box" id="box1">
	        		<label for="cms">CMS</label>
		            <select name="cms" id="cms">
		            <option value="">Select a cms</option>
		            <% 
       			    ArrayList<String> cms = (ArrayList<String>) request.getAttribute("cms_list");
		            for (String i : cms) {
				        %>
				                <option value='<%=i%>'> <%= i %> </option>
				    <% } %>
					</select>
	        	</div>
	        	
	        	<div class="box" id="box2">
	        		<label for="shopsystem">Shop System</label><br>
		            <input type="text" id="shopsystem" name="software_ss"><br>
	        	</div>
	        	
	        	<div class="box" id="box6">
	        		<a><button type="submit" value="Submit">ADD</button></a>
	        	</div>
        </div>
		</form>            
        <div class="item3">
        	<div class="filter">
        		<form action="" method="get">
        			<label for="filter">Search:</label>
					<input type="text" id="searchfilter" name="searchfilter">
					<br><br>
  				</form>
            </div>
        </div>

        <div class="item4">
        		<table class="table_cms">
                <thead>
                  <tr>
                  	<th scope="col">CMS</th>
                    <th scope="col">SHOP SYSTEM</th>
                  </tr>
                </thead>
                <tbody>
				<% ArrayList<ArrayList<String[]>> table = (ArrayList<ArrayList<String[]>>) request.getAttribute("table");
				for (ArrayList<String[]> i : table) { 
				for (String[] j : i){
				%>
				<tr>
                <td><%= j[0] %></td>
                <td><%= j[1] %></td>
	            </tr>
		        <% }} %>
                </tbody>
            </table>
        </div>
    </div>
    <script src="D:/Bug_Tracking/src/main/webapp/WEB-INF/lib/bootstrap/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>