<%@page import="ryerson.ca.frontend.helper.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${username}'s Homepage</title>
        <style> 
            body {
                height: 94vh;
                margin: 0;
                padding: 0;
                background-color: #19191D;
                font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif
            }

            .btn-text{
                text-decoration: none; 
            }

            .vert-center{
                margin: auto 0 auto 0; 
            }


            h1 {
                font-size: 100px; 
                margin: 0 0 0 0; 
                text-align: center;
                padding: 20px;
                background-color: #333;
                color: #fff;
                margin-bottom: 10px;
                flex-grow: 2;
            }

            .container{
                width: 100%; 
                height: 80%; 
                display: flex; 
                flex-direction: column; 
                align-items: center; 
                justify-content: center;
                align-content: center; 
                margin: 0 0 0 0;
                gap:10%;  
            }

            form{
                width: 80%;
                height: 40%;
                margin: 0 0 0 0; 
            }

            .btn{
                width: 100%; 
                height: 100%; 
                font-size: 50px; 
                background-color: #433BFF; 
                border-radius: 10px;
                font-weight: 900;
            }


            .btn:hover{
                background-color: white;
                color: #433BFF;
                transition: all 500ms ease-in-out; 
            }


            footer {
                background-color: #433BFF;
                color: #fff;
                text-align: center;
                position: fixed;
                scroll-behavior: smooth;
                width: 100%;
                bottom: 0;
                display: flex;
                flex-direction: row;
                margin: 3px;
                align-items: center;
                justify-content: space-evenly;
                border-radius: 10px; 
            }

            .button-style-logout {
                width: 150px;
                height: auto;
                color: white;
                font-weight: 500;
                background-color: rgb(234, 56, 56);
                border-radius: 10px;
                margin: 5px;
                padding: 10px;    
            }

            .button-style-logout:hover {
                opacity: 0.7;
                background: white;
                color: rgb(117, 27, 27);
            }

            .button-style-footer {
                width: auto;
                height: auto;
                color: white;
                font-weight: 500;
                background-color: #0c9f44;
                border-radius: 10px;
                margin: 5px;
                padding: 10px;
            }

            .footer-buttons > a{
                height: 100%; 
                width: auto; 
            }

            .button-style-footer:hover {
                opacity: 0.7;
                background: white;
                color: black;
            }

            .footer-buttons {
                width: 70%;
                display: flex; 
                align-items: center;
            }

            .lgout{
                padding: 0; 
                margin: 0; 
            }

        </style>
        <% 
            String username = (String) request.getSession().getAttribute("username"); 
            String token = (String)request.getSession().getAttribute("token");
            if(token == null){ 
                response.sendRedirect("index.html");
            }
        %>
    </head>
    <body>
        
        <h1>Hello ${username}</h1>
        <div class="container">
            <form action="FrontEnd" method="post">
                <input type="hidden" name="pageName" value="goDiscover"/>
                <button type="submit" class="btn">Expand Your Horizons?</button>
            </form>
            <form action="FrontEnd" method="post">
                <input type="hidden" name="pageName" value="goTrackLib"/>
                <button type="submit" class="btn">Show Me My Goods</button>
            </form> 
        </div>   
    </body>
    <footer>
        <p>Developed by Deep Patel and Yanny Patel</p>
        <div class="footer-buttons">
            <form class="lgout" action="FrontEnd" method="post">
                <input type="hidden" name="pageName" value="logout"/>
                <input type="submit" value="Logout" class="button-style-logout">
            </form>
            
        </div>
    </footer>
</html>
