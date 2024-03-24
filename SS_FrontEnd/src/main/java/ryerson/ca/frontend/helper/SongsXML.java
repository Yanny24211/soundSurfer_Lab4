/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ryerson.ca.frontend.helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author yannypatel
 */
@XmlRootElement(name = "songs")
@XmlAccessorType(XmlAccessType.FIELD)
public class SongsXML {
    @XmlElement(name = "song")
    private ArrayList<Song> songs; 
    
    public List<Song> getSongs(){
        return songs; 
    }
    public void setSong(ArrayList<Song> sgs){
        songs = sgs; 
    }
}
