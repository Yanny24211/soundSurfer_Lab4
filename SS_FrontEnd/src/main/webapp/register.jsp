<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SoundSurfer Register</title>
    <link rel="stylesheet" href="stylesheet.css">
</head>
<body>
    <div class="login-container">
        <h2>SoundSurfer Register</h2>
        <form action="FrontEnd" method="post">
            <input type="hidden" name="pageName" value="register"/>
            <div class="form-group"> 
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">Complete Registration</button>
        </form>
        <p>Have an account?  <a href="index.html" class="btn btn-register">Login</a></p>
    </div>    
</body>
</html>