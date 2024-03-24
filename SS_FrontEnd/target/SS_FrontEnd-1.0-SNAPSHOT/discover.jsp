<%@page import="java.io.IOException"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="ryerson.ca.frontend.business.Generate"%>
<%@page import="java.io.FileReader"%>

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
                // Read song IDs from the text file

                try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/deeps/Desktop/coe692_term_project/soundSurfer_Lab4/SS_discover/tracks.txt"))) {


                    String line;
                    while ((line = reader.readLine()) != null) {
                        songIds.add(line.trim()); // Add trimmed song ID to the list
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Shuffle the song IDs to get a random order
                java.util.Collections.shuffle(songIds);

                // Display iframes for the first 9 random song IDs
                for (int i = 0; i < Math.min(songIds.size(), 12); i++) {
            %>
                    <div class="song">
                        <iframe style="border-radius:12px" src="https://open.spotify.com/embed/track/<%= songIds.get(i) %>?utm_source=generator" width="100%" height="352" frameBorder="0" allowfullscreen="" allow="autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture" loading="lazy"></iframe>
                    </div>
            <% 
                }
            %>
        </div>
    </div>  
        
    <footer>
        <p>Developed by Deep Patel and Yanny Patel</p>
        <div class="footer-buttons">
            <a href="discover.jsp"><button class="button-style-footer">Discover</button></a>
            <a href="library.jsp"><button class="button-style-footer">Track Library</button></a>
            <a href="stats.jsp"><button class="button-style-footer">My Statistics</button></a>
            <form class=lgout action="FrontEnd" method="get"><input type="submit" value="Logout" class="button-style-logout"></form>
        </div>
    </footer>
</body>
</html>