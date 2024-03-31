/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ryerson.ca.discover.endpoint;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import ryerson.ca.discover.business.Generate;
import ryerson.ca.frontend.helper.GenSongsXML;


/**
 *
 * @author yannypatel
 */
@Path("discover")
public class DiscoverResource {

    @Context
    private UriInfo context;

    
    public DiscoverResource() {
    }

    
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("query") String query) {
        
        Generate gen = new Generate(); 
        GenSongsXML songs = new GenSongsXML(); 
        songs.setGenSong(gen.generateAndInsertSongs()); 
        
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(GenSongsXML.class);
        
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
     
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(songs, sw);
            System.out.println(sw.toString()); 
            return (sw.toString());
    
        } catch (JAXBException ex) {
            Logger.getLogger(Generate.class.getName()).log(Level.SEVERE, null, ex);
            return("error happened");
        }
    }

    /**
     * PUT method for updating or creating an instance of SearchResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
