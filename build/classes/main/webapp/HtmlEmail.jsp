<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">
	 <html lang='en'>
	 <head>
    	 <meta name='viewport' content='width=device-width, initial-scale=1.0'>
	     <title>USER APPROVAL</title>
	     <script type="text/javascript" src="/pracc/src/main/webapp/js/main.js"></script>
	     <link rel="stylesheet" href="css/bootstrap.min.css">
	 </head>
	 <body 
	 style="background-color: #0082c8;
     color: white;
     display: flex;
     align-items: center;
     justify-content: center;
     height: 50vh;
     margin: 0;
     padding: 0;">
    
     	<div 
     	class='container' 
     	style="background-color: #002642;
    	padding: 30px;
    	border-radius: 15px;
    	text-align: center;">
      	<h1 class='title'>
        	 BUG TRACKING SYSTEM
      	</h1>
      	<h3>
 	    	 We have a user approval request.
      	</h3>
     	<ol>
     	<li><h3>Name : <%= request.getAttribute("firstname") %> <%= request.getAttribute("lastname") %></h3></li>
     	    <li><h3>Email : <%= request.getAttribute("email") %></h3></li>
     	<li><h3>Role : <%= request.getAttribute("role") %></h3></li>
     	</ol>
     		<a href='http://localhost:8081/pracc/emailtoadmin'><button 
     	    class='btn' 
     	    id='approved' 
    		style="color: white;
    		padding: 10px;
    		border-radius: 5px;
    		margin: 10px;
    		background-color: green;"
    		>
    			view
     	    </button></a>
     	    
     	    
	 	</div>
	 </body>
	 </html>