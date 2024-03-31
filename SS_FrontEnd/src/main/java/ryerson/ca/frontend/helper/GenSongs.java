/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ryerson.ca.frontend.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yannypatel
 */
@XmlRootElement(name = "song")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenSongs {
    private String songId;  

    public GenSongs(){
    }
    public GenSongs(String id) {
        this.songId = id;
    }

    public String getId(){
        return this.songId; 
    }
    public void setId(String id){
        this.songId = id; 
    }

}