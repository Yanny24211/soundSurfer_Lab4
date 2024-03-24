/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ryerson.ca.tracklibrary.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import ryerson.ca.tracklibrary.helper.Song;
import ryerson.ca.tracklibrary.helper.SongsXML;
import ryerson.ca.tracklibrary.persistence.Song_CRUD;

/**
 *
 * @author yannypatel
 */
public class SearchSong {
    public  SongsXML getSongsByQuery(String query){
       ArrayList<Song> songs = Song_CRUD.searchForSongs(query);
       
        SongsXML sgs;
        sgs = new SongsXML();
        sgs.setSong(songs);
        return (sgs);
    }
}
