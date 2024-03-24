package ryerson.ca.frontend.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generate {
    public static void generateAndInsertSongs() {
        try {
            List<String> songIds = readSongIdsFromFile("C:\\Users\\deeps\\Desktop\\coe692_term_project\\soundSurfer_Lab4\\SS_discover\\tracks.txt");
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
            String line;
            while ((line = reader.readLine()) != null) {
                songIds.add(line.trim()); 
            }
        }
        return songIds;
    }

    private static List<String> selectRandomSongs(List<String> songIds) {
        Collections.shuffle(songIds);
        return songIds.subList(0, Math.min(songIds.size(), 9));
    }

    private static void generateIframe(String songId) {
        String iframeSrc = "https://open.spotify.com/embed/track/" + songId + "?utm_source=generator";
        String iframeHtml = "<div class=\"song\">\n" +
                            "    <iframe style=\"border-radius:12px\" src=\"" + iframeSrc + "\" width=\"100%\" height=\"352\" frameBorder=\"0\" allowfullscreen=\"\" allow=\"autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture\" loading=\"lazy\"></iframe>\n" +
                            "</div>";
        System.out.println(iframeHtml);
    }
}