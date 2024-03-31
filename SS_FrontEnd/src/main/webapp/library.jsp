<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ryerson.ca.frontend.helper.Song"%>
<%@page import="ryerson.ca.frontend.helper.User"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Soundsurfer Track Library</title>
        <link rel="stylesheet" href="librarystyles.css">
        <style>
            .form-container {
                width: 300px; /* Adjust as needed */
                margin: 0 auto; /* Center the form horizontally */
                padding: 20px;
                background-color: #f9f9f9;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center; /* Center-align the form contents */
            }

            /* Style for form labels */
            .form-label {
                font-weight: bold;
                margin-bottom: 5px;
                display: block; /* Ensure labels are displayed one below the other */
            }

            /* Style for form input fields */
            .form-input {
                width: calc(100% - 16px); /* Adjust input width to accommodate padding and border */
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            /* Style for the submit button */
            .form-submit {
                width: calc(100% - 16px); /* Adjust button width to accommodate padding and border */
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s; /* Smooth transition for hover effect */
            }

            .form-submit:hover {
                background-color: #50C878;
            }

            .top-bar h1 {
                color: white;
            }

            #addedSongs {
                margin-top: 20px; /* Add some spacing between the form and the text list */
                padding: 0 20px;
            }

            #addedSongs ul {
                list-style-type: none; /* Remove bullet points */
                padding: 0;
            }

            #addedSongs ul li {
                margin-bottom: 10px; /* Add some vertical spacing between list items */
                color: white; /* Text color */
                font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; /* Font family */
            }

            .formDirection {
                display: flex;
                flex-direction: row;
                padding: 15px;
                gap: 10px;
            }


            .songListHeader {
                color: white;
            }

            .button-style-header-special {
                width: 120px;
                height: auto;
                color: white;
                font-weight: 500;
                background-color: #0c9f44;
                padding: 5px;
                border-radius: 10px;
                margin: 26.5px;
            }

            .main-heading {
                text-align: center;
                top: 0;
                padding: 5px;
                width: 100%;
                background-color: #333;
                color: #fff;
                font-family:Verdana, Geneva, Tahoma, sans-serif;
            }
             table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #eee;
            color: black;
        }

        /* Style for the remove button */
        .remove-button {
            background-color: #f44336;
            color: white;
            border: none;
            padding: 6px 12px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }
        </style>
    </head>
    <% 
            String username = (String) request.getSession().getAttribute("username"); 
            String token = (String)request.getSession().getAttribute("token");
            if(token == null){ 
                response.sendRedirect("index.html");
            }
    %>
    
        <div class="top-bar">
            <div class="main-heading">
                <h1>Track Management Library</h1>
            </div>
            <div class="buttons">
                <button class="button-style-header-special" onclick="showAddSongForm()">Add Song</button>
                <!--<button class="button-style-header">Delete Song</button>-->
            </div>
        </div>

        <div id="addSongForm" style="display: none;">
            <form action="AddSong" method="post">
                <div class="formDirection">
                    <label for="songName">Song Name:</label>
                    <input type="text" id="songName" name="songName" required><br>

                    <label for="artist">Artist:</label>
                    <input type="text" id="artist" name="artist" required><br>

                    <label for="rating">Rating:</label>
                    <input type="number" id="rating" name="rating" min="1" max="5" required><br>
                </div>
                <input type="submit" value="Confirm">
            </form>
        </div>
        <h2>Search Songs</h2> 
        <form action="FrontEnd" method="post"> 
            <input type="hidden" name="pageName" value="search"/>
                Search in song titles and artist names
            <input type="text"  name="query">
          <input type="submit">
        </form>
        <div id="addedSongs" class="songListHeader">
             <h2>Added Songs:</h2>
    <table>
        <thead>
            <tr>
                <th>Title</th>
                <th>Artist</th>
                <th>Rating</th>
            </tr>
        </thead>
        <tbody>
            <% 
                try {
                    // Establish database connection and create PreparedStatement
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_LBS?autoReconnect=true&useSSL=false", "root", "student123");
                    PreparedStatement ps = con.prepareStatement("SELECT title, artist, rating FROM user_song");
                    ResultSet rs = ps.executeQuery();

                    // Iterate through the result set and display the data in the table
                    while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("title") %></td>
                <td><%= rs.getString("artist") %></td>
                <td><%= rs.getInt("rating") %></td>
            </tr>
            <%
                    }

                    // Close the database connection and release resources
                    rs.close();
                    ps.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            %>
        </tbody>
    </table>
        </div>

        <footer>
            <p>Developed by Deep Patel and Yanny Patel</p>
            <div class="footer-buttons">
                <form action="FrontEnd" method="post">
                    <input type="hidden" name="pageName" value="goDiscover"/>
                    <input type="submit" value="Discover" class="button-style-footer">
                </form>
                <a href="library.jsp"><button class="button-style-footer">Track Library</button></a>
                <a href="stats.jsp"><button class="button-style-footer">My Statistics</button></a>
                <form class="lgout" action="FrontEnd" method="post">
                <input type="hidden" name="pageName" value="logout"/>
                <input type="submit" value="Logout" class="button-style-logout">
                </form>
            </div>
        </footer>

        <script>
            function showAddSongForm() {
                document.getElementById("addSongForm").style.display = "flex";
                document.getElementById("addSongForm").style.position = "relative";
                document.getElementById("addSongForm").style.padding = "20px";
                document.getElementById("addSongForm").style.margin = "10px";
                document.getElementById("addSongForm").style.background = "black";
                document.getElementById("addSongForm").style.color = "white";
            }
        </script>
    
</html>