/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ryerson.ca.library.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import ryerson.ca.library.helper.Song;
import ryerson.ca.library.helper.SongsXML;
import ryerson.ca.library.persistence.Song_CRUD;

/**
 *
 * @author yannypatel
 */
public class SearchSong {
    public  SongsXML getSongsByQuery(String query){
       Set<Song> songs = Song_CRUD.searchForSongs(query);
       
        SongsXML sgs;
        sgs = new SongsXML();
        sgs.setSong(new ArrayList(songs));
        return (sgs);
    }
}
