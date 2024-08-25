<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <title>Login</title>
</head>
<body>
    <div class="container">
        <h1 class="title">Log In</h1>
        <form action="home" method="POST">
            <!-- Getting username input -->
            <label for="username">E-mail id</label><br>
            <input placeholder="E-mail" type="text" name="username" id="username" required><br>

            <!-- Getting password input -->
            <label for="password">Password</label><br>
            <input placeholder="Password" type="password" name="password" id="password" required><br>

            <!-- Redirector to signin.jsp -->
            <a href="signin.jsp">Not a member? <b>Sign In</b></a><br><br>
           
            <!-- Submitting details for validation to login.java -->
            <button type="submit" class="button" id="login_btn">LogIn</button><br><br>

            <!-- Hidden field to pass the "wrong credentials" condition -->
            <input type="hidden" id="wrongCredentials" value="${requestScope.wrong eq true}">

            <!-- Placeholder for the wrong credentials message -->
            <h5 id="wrongCredentialsMessage" style="color:red; display:none;">You gave Wrong Credentials</h5>
        </form>
    </div>

    <script src="D:/Bug_Tracking/src/main/webapp/WEB-INF/lib/bootstrap/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // JavaScript to check the hidden field and display the message
        document.addEventListener('DOMContentLoaded', function() {
            const wrongCredentials = document.getElementById('wrongCredentials').value;
            if (wrongCredentials === 'true') {
                document.getElementById('wrongCredentialsMessage').style.display = 'block';
            }
        });
    </script>
</body>
</html>