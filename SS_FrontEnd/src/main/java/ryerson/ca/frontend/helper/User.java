/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ryerson.ca.frontend.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User{
    private String username;
    private String password; 
    private int numSongs;
    private int numArtists;
    private String favGenre;
    private String favArtist;
    private String favSong;
    
    public User(String user, String pass){
        username = user; 
        password = pass; 
    }
    
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
    
    public String getUsername(){
        return this.username; 
    }
    
    public void setUsername(String name){
        username = name; 
    }
  
    public String getPassword(){
        return this.password; 
    }
    
    public void setPassword(String pass){   
        password = pass; 
    }
    
    public String getNumSongs() {
        String num_songs = "0"; 
        try{
            Connection con = getCon(); 
            String userQ = "select num_songs from theUser where username like \"" + this.username + "\""; 
            //System.out.println(userQ); 
            PreparedStatement ps = con.prepareStatement(userQ); 

            try(ResultSet rs = ps.executeQuery()){
                //System.out.println("Statement: " + rs);
                //Try printing result set
                
                while(rs.next()){
                    num_songs = rs.getString("num_songs");
                    
                }
                con.close();   
            }
            catch(Exception e){System.out.println("getNumSongs: "+ e);}
            
        }
        catch(Exception e){System.out.println("#Songs Retrieval Failed: " + e);}
        return num_songs; 

    }
    
    public void setNumSongs(int numSongs) {
        this.numSongs = numSongs;
    }
    
    public String getNumArtists() {
        String num_artists = "0"; 
        try{
            Connection con = getCon(); 
            String userQ = "select num_artists from theUser where username like \"" + this.username + "\""; 
            //System.out.println(userQ); 
            PreparedStatement ps = con.prepareStatement(userQ); 

            try(ResultSet rs = ps.executeQuery()){
                //System.out.println("Statement: " + rs);
                //Try printing result set
                
                while(rs.next()){
                    num_artists = rs.getString("num_artists");
                    
                }
                con.close();   
            }
            catch(Exception e){System.out.println("getNumArtists: "+ e);}
            
        }
        catch(Exception e){System.out.println("#Artists Retrieval Failed: " + e);}
        return num_artists; 
    }
   
    public void setNumArtists (int numArtists) {
        this.numArtists = numArtists;
    }
    
    public String getFavGenre() {
        String fav_genre = "Rap"; 
        try{
            Connection con = getCon(); 
            String userQ = "select fav_genre from theUser where username like \"" + this.username + "\""; 
            //System.out.println(userQ); 
            PreparedStatement ps = con.prepareStatement(userQ); 

            try(ResultSet rs = ps.executeQuery()){
                //System.out.println("Statement: " + rs);
                //Try printing result set
                
                while(rs.next()){
                    fav_genre = rs.getString("fav_genre");
                    
                }
                con.close();   
            }
            catch(Exception e){System.out.println("getFavGenre: "+ e);}
            
        }
        catch(Exception e){System.out.println("Genre Retrieval Failed: " + e);}
        return fav_genre; 
    }
   
    public void setFavGenre (String favGenre) {
        this.favGenre = favGenre;
    }
    
    public String getFavArtist() {
        String fav_artist = "Kanye"; 
        try{
            Connection con = getCon(); 
            String userQ = "select fav_artist from theUser where username like \"" + this.username + "\""; 
            //System.out.println(userQ); 
            PreparedStatement ps = con.prepareStatement(userQ); 

            try(ResultSet rs = ps.executeQuery()){
                //System.out.println("Statement: " + rs);
                //Try printing result set
                
                while(rs.next()){
                    fav_artist = rs.getString("fav_artist");
                    
                }
                con.close();   
            }
            catch(Exception e){System.out.println("getFavArtist: "+ e);}
            
        }
        catch(Exception e){System.out.println("Artist Retrieval Failed: " + e);}
        return fav_artist; 
    }
   
    public void setFavArtist (String favArtist) {
        this.favArtist = favArtist;
    }
    
    public String getFavSong() {
        String fav_song = "Flashing Lights"; 
        try{
            Connection con = getCon(); 
            String userQ = "select fav_song from theUser where username like \"" + this.username + "\""; 
            //System.out.println(userQ); 
            PreparedStatement ps = con.prepareStatement(userQ); 

            try(ResultSet rs = ps.executeQuery()){
                //System.out.println("Statement: " + rs);
                //Try printing result set
                
                while(rs.next()){
                    fav_song = rs.getString("fav_song");
                    
                }
                con.close();   
            }
            catch(Exception e){System.out.println("getFavSong: "+ e);}
            
        }
        catch(Exception e){System.out.println("Song Retrieval Failed: " + e);}
        return fav_song; 
    }
    
    public void setFavSong(String favSong) {
        //later when array of songs is passed, a count of each can be recorded
        //returning the highest one
        this.favSong = favSong;
    }
}
 