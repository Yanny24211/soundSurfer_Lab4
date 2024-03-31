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
public class GenSongsXML {
    @XmlElement(name = "song")
    private ArrayList<GenSongs> songs; 
    
    public List<GenSongs> getGenSongs(){
        return songs; 
    }
    public void setGenSong(ArrayList<GenSongs> sgs){
        songs = sgs; 
    }
}

