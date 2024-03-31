package ryerson.ca.tracklibrary.business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import ryerson.ca.tracklibrary.helper.Song;
import ryerson.ca.tracklibrary.helper.User;

@WebServlet("/AddSong")
public class AddSong extends HttpServlet {
    private static Connection getCon(){
        Connection con = null; 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_LBS?autoReconnect=true&useSSL=false", "root", "student123");
            System.out.println("Connection Established");
        }
        catch(Exception e){System.out.println("Connection Failed: " + e);} 
        return con;
    }
    
    public void addSong(Song song, int user, String user1) {
    String title = song.getTitle();
    String artist = song.getArtist();
    int rating = song.getRating();

    try {
        Connection con = getCon();
        String insertQuery = "INSERT INTO user_song (title, artist, rating) VALUES ('" + title + "', '" + artist + "', " + rating + ")";
        PreparedStatement ps = con.prepareStatement(insertQuery);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Song inserted into database");
        } else {
            System.out.println("Failed to insert song into database");
        }

        con.close();
    } catch (Exception e) {
        System.out.println("Error: " + e);
    }
}

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String songName = request.getParameter("songName");
        String artist = request.getParameter("artist");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String user = (String) request.getSession().getAttribute("username");
        // Create a new Song object
        Song newSong = new Song(songName, artist, rating);
       
        // Retrieve the list of added songs from session
        ArrayList<Song> addedSongs = (ArrayList<Song>) request.getSession().getAttribute("addedSongs");

        // If the list doesn't exist, create a new one
        if (addedSongs == null) {
            addedSongs = new ArrayList<>();
            request.getSession().setAttribute("addedSongs", addedSongs);
        }

        // Add the new song to the list
        addedSongs.add(newSong);
        int len = addedSongs.size();
        addSong(newSong, len, user); 
        // Redirect back to the library page
        response.sendRedirect("library.jsp");
    }
}