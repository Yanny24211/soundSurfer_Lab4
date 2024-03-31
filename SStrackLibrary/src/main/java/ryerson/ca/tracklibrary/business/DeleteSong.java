package ryerson.ca.tracklibrary.business;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import ryerson.ca.tracklibrary.helper.Song;

@WebServlet("/DeleteSong")
public class DeleteSong extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the index of the song to be deleted from the request parameter
        int songIndex = Integer.parseInt(request.getParameter("songIndex"));

        // Retrieve the list of songs from the session
        ArrayList<Song> addedSongs = (ArrayList<Song>) request.getSession().getAttribute("addedSongs");

        // Check if the list is not null and the index is valid
        if (addedSongs != null && songIndex >= 0 && songIndex < addedSongs.size()) {
            // Remove the song from the list
            addedSongs.remove(songIndex);
            // Update the session attribute
            request.getSession().setAttribute("addedSongs", addedSongs);
        }

        // Redirect back to the library.jsp page
        response.sendRedirect("library.jsp");
    }
}