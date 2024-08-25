<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">
	 <html lang='en'>
	 <head>
    	 <meta name='viewport' content='width=device-width, initial-scale=1.0'>
	     <title>Approved</title>
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
 	    	 You have been approved.
      	</h3>
     	<h4>Hello user! Welcome to Bug Tracking System<br>Your password to login into the software is given below.<br>
     	Kindly keep it confidential.<br>
     	Thank you!</h4>
	    <h5>password : <%= session.getAttribute("pswd") %></h5>

     		<a href='http://localhost:8081/pracc/login.jsp'><button 
     	    class='btn' 
     	    id='approved' 
    		style="color: white;
    		padding: 10px;
    		border-radius: 5px;
    		margin: 10px;
    		background-color: green;"
    		>
    			Login
     	    </button></a>
     	    
     	    
	 	</div>
	 </body>
	 </html>