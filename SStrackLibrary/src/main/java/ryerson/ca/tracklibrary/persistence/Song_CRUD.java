/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ryerson.ca.tracklibrary.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import ryerson.ca.tracklibrary.helper.Song;

/**
 *
 * @author yannypatel
 */
public class Song_CRUD {
    private static Connection getCon(){
    Connection con=null;
     try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_LBS?autoReconnect=true&useSSL=false", "root", "student123");
         System.out.println("Connection established. (SONG_CRUD CON CHECK)");
     }
     catch(Exception e){ System.out.println(e);}
     return con;
    }
    
    public static ArrayList<Song> searchForSongs(String query, String user){
        ArrayList<Song> songs= new ArrayList<Song>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            Connection con= getCon();
            
            String q = "SELECT title, artist, rating FROM user_song WHERE (title LIKE ? OR artist LIKE ?) AND username LIKE ?";
            ps = con.prepareStatement(q);
            
            //System.out.println("%" + query + "%");
            ps.setString(1, '%' + query + '%');
            
            ps.setString(2, '%' + query + '%');
            
            //System.out.println("'" + user + "'");
            ps.setString(3, "" + user + "");
            
            System.out.println(ps);
			
            rs=ps.executeQuery();
            while(rs.next()){
		//been= new UserInfo();
		String title=rs.getString("title");
                String artist =rs.getString("artist");
                int rating = Integer.parseInt(rs.getString("rating"));
                             
                Song song = new Song(title, artist, rating);
                songs.add(song);
                                
            }
			
            con.close();

	}catch(Exception e){System.out.println(e);}
            
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+songs.size());
        System.out.println("SONGS ARRAYLIST: " + songs); 
        return songs;
        
    }
}
