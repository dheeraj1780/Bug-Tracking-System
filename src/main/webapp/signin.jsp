<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/signin.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
    <title>Signin</title>
</head>
<body>
    <div class="container">
        <h1 class="title" >Sign In</h1>
        <form action="register" method="POST">
        
<!--input firstname-->
            <label for="firstname">Firstname</label><br>
            <input placeholder="First Name" type="text" name="firstname" id="firstname" required><br>
			
<!--input lastname-->
            <label for="lastname">Lastname</label><br>
            <input placeholder="Last Name" type="text" name="lastname" id="lastname" required><br>
			
<!--input email id-->
            <label for="username">E-mail id</label><br>
            <input placeholder="E-mail" type="text" name="username" id="username" required><br>

<!--input role-->
            <label for="role">Role</label><br>
            <select id="role" name="role">
                <option value="Incharge">Incharge</option>
                <option value="Tester">Tester</option>
                <option value="Developer">Developer</option>
            </select><br>
            <br>
            
<!--login redirector-->
            <a href="login.jsp">Already a member? <b>Log In</b></a><br><br>
            
<!--submit button-VerificationSender.java-->
            <button type="submit" class="button" id="signin_btn">Submit</button><br>   
        </form>
    </div>
    <script src="D:/Bug_Tracking/src/main/webapp/WEB-INF/lib/bootstrap/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>