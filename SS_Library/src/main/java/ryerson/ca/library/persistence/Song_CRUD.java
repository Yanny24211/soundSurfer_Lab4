/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ryerson.ca.library.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import ryerson.ca.library.helper.Song;

/**
 *
 * @author yannypatel
 */
public class Song_CRUD {
    private static Connection getCon(){
    Connection con=null;
     try{
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_LBS?autoReconnect=true&useSSL=false", "root", "student123");
         System.out.println("Connection established.");
     }
     catch(Exception e){ System.out.println(e);}
     return con;
    }
    
    public static Set<Song> searchForSongs(String query){
        Set<Song> songs= new HashSet<Song>();
        try{
            Connection con= getCon();
            
            String q = "select * from user_song;";
                        System.out.println(q);
			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
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
        return songs;
        
    }
}
