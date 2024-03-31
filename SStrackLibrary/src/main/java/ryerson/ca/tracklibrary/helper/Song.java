/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ryerson.ca.tracklibrary.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yannypatel
 */
@XmlRootElement(name = "song")
@XmlAccessorType(XmlAccessType.FIELD)
public class Song {
    private String title;
    private String artist; 
//    private String prodComp; 
//    private String genre; 
//    private double runtime; 
//    private String songFormat; 
//    private String songDescription; 
//    private String countryOfOrigin; 
    private int rating; 
//    private String releaseDate; 

//    public Song(String title, String artist, String genre, double runtime, int rating, String releaseDate, String countryOfOrigin, String prodComp, String songFormat, String songDescription){
//        this.title = title; 
//        this.artist = artist; 
//        this.genre = genre; 
//        this.runtime = runtime; 
//        this.releaseDate = releaseDate; 
//        this.countryOfOrigin = countryOfOrigin; 
//        this.prodComp = prodComp; 
//        this.songFormat = songFormat; 
//        this.songDescription = songDescription; 
//        if(rating>=0 && rating<= 5){
//            this.rating = rating; 
//        }
//        else{
//            this.rating = 0;
//        }
//    }
    public Song(){
 
    }
    public Song(String title, String artist, int rating) {
        this.title = title;
        this.artist = artist;
        this.rating = rating;
    }

    public String getTitle(){
        return this.title; 
    }
    public void setTitle(String name){
        this.title = name; 
    }

    public String getArtist(){
        return this.artist; 
    }
    public void setArtist(String name){
        this.artist = name; 
    }

    public int getRating(){
        return this.rating; 
    }
    public void setRating(int rate){
        if(rate>=0 && rate<= 5){
            this.rating = rate; 
        }
        else{
            this.rating = 0;
        }
    }

}
