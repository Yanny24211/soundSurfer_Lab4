package ryerson.ca.frontend.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ryerson.ca.frontend.helper.Song;

public class Generate {
    public static void generateAndInsertSongs() {
        try {

            List<String> songIds = readSongIdsFromFile("C:/Users/deeps/Desktop/coe692_term_project/soundSurfer_Lab4/SS_discover/tracks.txt");
            List<String> selectedSongIds = selectRandomSongs(songIds);
            for (String songId : selectedSongIds) {
                generateIframe(songId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read Spotify song IDs from a file
    private static List<String> readSongIdsFromFile(String filePath) throws IOException {
        List<String> songIds = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = null;

                songIds.add(line.trim()); 
            }
        return null;
        }

    private static List<String> selectRandomSongs(List<String> songIds) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void generateIframe(String songId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    }


