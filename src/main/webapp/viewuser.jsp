<%
	if(session.getAttribute("username")==null){
		response.sendRedirect("login.jsp");
	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/viewuser.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>USER VIEW</title>
</head>
<body>
    <div class="container">
    

        <div class="header">
            <h1>User Details</h1>
        </div>
        <div class="content">
<%--getting the attributes and displaying in the window --%>
            <h3>Username : <%= request.getAttribute("username") %></h3>
            <h3>Name : <%= request.getAttribute("first_name") %> <%= request.getAttribute("last_name") %></h3>
            <h3>Role : <%= request.getAttribute("role") %></h3>
            <h3>Current Status : <%= request.getAttribute("status") %></h3>
            
            <% String userStatus = (String) request.getAttribute("status"); %>
            <% String userName = (String) request.getAttribute("username"); %>
            <% request.setAttribute("status",userStatus); %>
            <% request.setAttribute("username",userName); %>
            
            
<%--setting the status and checking whether user is waiting or not --%>
            <c:set var="status" value="${requestScope.status}"></c:set>
            <c:set var="string2" value="waiting"></c:set>
            
            <c:if test="${status ne string2}">
				<a href="/pracc/users_approved"><button type="submit" name="action" value="block " id="edit_btn">Edit</button></a>
			</c:if>
			<c:if test="${status eq string2}">
				<a href="/pracc/user_approved?status=approved&username=${requestScope.username}"><button >Approve</button></a>
				<a href="/pracc/user_approved?status=disapproved&username=${requestScope.username}"><button >Disapprove</button></a>
				<a href="/pracc/user_approved?status=blocked&username=${requestScope.username}"><button >block</button></a>
			</c:if>

        </div>
        <footer>
<%--get back to the users menu --%>
        <a href="/pracc/users"><button id="back_btn"> &lt; Back </button></a>
        </footer>
    </div>
</body>
</html>