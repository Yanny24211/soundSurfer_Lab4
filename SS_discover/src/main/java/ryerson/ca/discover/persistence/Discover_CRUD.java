package ryerson.ca.discover.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Discover_CRUD {
    private final Connection connection;

    // Constructor
    public Discover_CRUD(Connection connection) {
        this.connection = connection;
    }

    // Method to add a discovered song
    public void addDiscoveredSong(String spotifySongId) throws SQLException {
        String query = "INSERT INTO discovered_song (spotify_song_id) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, spotifySongId);
            statement.executeUpdate();
        }
    }

    // Method to clear all discovered songs
    public void clearDiscoveredSongs() throws SQLException {
        String query = "DELETE FROM discovered_song";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }
}