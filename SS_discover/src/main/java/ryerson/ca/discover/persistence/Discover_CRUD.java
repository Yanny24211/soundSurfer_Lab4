/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ryerson.ca.discover.persistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Discover_CRUD {
    private final Connection connection;

    public Discover_CRUD(Connection connection) {
        this.connection = connection;
    }

    public void addDiscoveredSong(String spotifySongId) throws SQLException {
        String query = "INSERT INTO discovered_song (spotify_song_id) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, spotifySongId);
            statement.executeUpdate();
        }
    }

    public void clearDiscoveredSongs() throws SQLException {
        String query = "DELETE FROM discovered_song";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }
}