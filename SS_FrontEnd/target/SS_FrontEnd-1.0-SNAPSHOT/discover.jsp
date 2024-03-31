<%@page import="ryerson.ca.frontend.helper.GenSongs"%>
<%@page import="ryerson.ca.frontend.helper.Song"%>
<%@page import="ryerson.ca.frontend.helper.GenSongsXML"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SoundSurfer Discover</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="panels-container">
        <h2 class="title">Discover</h2>
        <div class="suggestions">
            <% 
            String username = (String) request.getSession().getAttribute("username"); 
            String token = (String)request.getSession().getAttribute("token");
            if(token == null){ 
                response.sendRedirect("index.html");
            }
            %>
            <% 
                // Read song IDs from the text file
                GenSongsXML songs = (GenSongsXML) request.getAttribute("genSongResults");
                System.out.println("SONGS: " + songs);
                if(songs != null){
                // Display iframes for the first 9 random song IDs
                    for (GenSongs song : songs.getGenSongs()) {
                        System.out.println(song.getId());
            %>
                    <div class="song">
                        <iframe style="border-radius:12px" src="https://open.spotify.com/embed/track/<%= song.getId() %>?utm_source=generator" width="100%" height="352" frameBorder="0" allowfullscreen="" allow="autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture" loading="lazy"></iframe>
                    </div>
            <% 
                }
            }   
            %>
        </div>
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
</body>
</html>
