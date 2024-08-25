<%
	if(session.getAttribute("username")==null){
		response.sendRedirect("login.jsp");
	}
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <title>Bug_Tracker</title>
</head>
<body>
	
    <header>
<!-- logo of the software -->
        <img class="logo" src="/pracc/src/main/webapp/images/logo.jpg" alt="logo">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="nav_links">
<!-- home menu -->
                    <li><a href="index.jsp">Home</a></li>
                    
<!-- admin specific menu named users -->
                    <c:if test="${sessionScope.isAdmin eq true}">
					    <li><a href="http://localhost:8081/pracc/users">Users</a></li>
					    <li><a href="http://localhost:8081/pracc/shopsystems">Shop Systems</a></li>
					    <li><a href="http://localhost:8081/pracc/cms">CMS</a></li>
					    <li><a href="http://localhost:8081/pracc/users">Report</a></li>
					</c:if>
					
					<c:if test="${sessionScope.isAdmin != true}">
					    <li><a href="http://localhost:8081/pracc/reports">Reports</a></li>
					</c:if>
            </ul>
        </nav>
        
<!-- log out menu -->
        <a href="http://localhost:8081/pracc/logout"><button>Logout</button></a>
    </header>
    <script src="D:/Bug_Tracking/src/main/webapp/WEB-INF/lib/bootstrap/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>