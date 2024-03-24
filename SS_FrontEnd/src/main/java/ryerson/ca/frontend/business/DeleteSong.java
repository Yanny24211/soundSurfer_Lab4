package ryerson.ca.frontend.business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import ryerson.ca.frontend.helper.Song;

@WebServlet("/DeleteSong")
public class DeleteSong extends HttpServlet {
    private static Connection getCon(){
        Connection con = null; 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account?autoReconnect=true&useSSL=false", "root", "student");
            System.out.println("Connection Established");
        }
        catch(Exception e){System.out.println("Connection Failed: " + e);} 
        return con;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the index of the song to be deleted from the request parameter
        int songIndex = Integer.parseInt(request.getParameter("songIndex"));

        // Retrieve the list of songs from the session
        ArrayList<Song> addedSongs = (ArrayList<Song>) request.getSession().getAttribute("addedSongs");

        // Check if the list is not null and the index is valid
        if (addedSongs != null && songIndex >= 0 && songIndex < addedSongs.size()) {
            // Get the song object to be deleted
            Song songToDelete = addedSongs.get(songIndex);
            
            // Perform the deletion from the database
            try {
                Connection con = getCon();
                String deleteQuery = "DELETE FROM user_song WHERE title = ?";
                PreparedStatement ps = con.prepareStatement(deleteQuery);
                ps.setString(1, songToDelete.getTitle());
                
                int rowsAffected = ps.executeUpdate();
                
                if (rowsAffected > 0) {
                    System.out.println("Song deleted from database");
                } else {
                    System.out.println("Failed to delete song from database");
                }
                
                con.close();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            
            // Remove the song from the list
            addedSongs.remove(songIndex);
            // Update the session attribute
            request.getSession().setAttribute("addedSongs", addedSongs);
        }

        // Redirect back to the library.jsp page
        response.sendRedirect("library.jsp");
    }
}
