/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.frontend.business;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import ryerson.ca.frontend.helper.GenSongsXML;
import ryerson.ca.frontend.helper.Song;
import ryerson.ca.frontend.helper.SongsXML;
import ryerson.ca.frontend.helper.User; 

public class Business {
    
    private static Connection getCon(int i){
        Connection con = null; 
        try{
            switch(i){
                case 1: 
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Account_LBS?autoReconnect=true&useSSL=false", "root", "student123");
                    System.out.println("Connection Established");
                    break; 
                case 2:
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_LBS?autoReconnect=true&useSSL=false", "root", "student123");
                    System.out.println("Connection Established");
                    break; 
                case 3:
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Discover_LBS?autoReconnect=true&useSSL=false", "root", "student123");
                    System.out.println("Connection Established");
                    break; 
            } 
                
        }
        catch(Exception e){System.out.println("Connection Failed: " + e);} 
        return con;
    }
    
    public static void addUser(User user){
        String uname = user.getUsername(); 
        String pass = user.getPassword(); 
        try{
            
            Connection con; 
            con = getCon(1);
            String userQ = "insert into theUser(user_id, username, date_of_birth, password) values (null, \"" + uname + "\", null, \"" + pass + "\")"; 
            PreparedStatement  ps = con.prepareStatement(userQ); 
           
            int rowsAffected = ps.executeUpdate();
            System.out.print("# of Rows: " + rowsAffected);
            if(rowsAffected > 0){
                 System.out.println("Rows added"); 
            }
            else{
                System.out.println("Rows not added"); 
            }
            con.close();
            
            con = getCon(2);
            userQ = "insert into theUser(user_id, username, date_of_birth, password) values (null, \"" + uname + "\", null, \"" + pass + "\")"; 
            ps = con.prepareStatement(userQ); 
           
            rowsAffected = ps.executeUpdate();
            System.out.print("# of Rows: " + rowsAffected);
            if(rowsAffected > 0){
                 System.out.println("Rows added"); 
            }
            else{
                System.out.println("Rows not added"); 
            }
            con.close();
            
            con = getCon(3);
            userQ = "insert into theUser(user_id, username, date_of_birth, password) values (null, \"" + uname + "\", null, \"" + pass + "\")"; 
            ps = con.prepareStatement(userQ); 
           
            rowsAffected = ps.executeUpdate();
            System.out.print("# of Rows: " + rowsAffected);
            if(rowsAffected > 0){
                 System.out.println("Rows added"); 
            }
            else{
                System.out.println("Rows not added"); 
            }
            con.close();
        }
        catch(Exception e){System.out.println(e);}
 
    }
    
    public static User getUser(String username){  
        User person = null;
        try{
            Connection con = getCon(1); 
            String userQ = "select * from theUser where username like \"" + username + "\""; 
            //System.out.println(userQ); 
            PreparedStatement ps = con.prepareStatement(userQ); 

            try(ResultSet rs = ps.executeQuery()){
                //System.out.println("Statement: " + rs);
                //Try printing result set
                
                while(rs.next()){
                    String user_id = rs.getString("user_id");
                    String user = rs.getString("username"); 
                    String dob = rs.getString("date_of_birth");
                    String pass = rs.getString("password");
                    System.out.println("User: " + user);
                    System.out.println("Pass: " + pass);
                    person = new User(user, pass);
                }
                con.close();   
            }
            catch(Exception e){System.out.println("RS: "+ e);}
            
        }
        catch(Exception e){System.out.println("User Retrieval Failed: " + e);}
        return person; 
    }
    
    public static boolean validate(String username, String password){
        User user = getUser(username);
        return user != null && user.getPassword().equals(password); 
    }



    public static SongsXML getServices(String query, String token, String user) throws IOException {
        if (user == null || user.isEmpty()) {
        throw new IllegalArgumentException("Username parameter is null or empty");
        }
        
        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget = searchclient.target("http://localhost:8080/SStrackLibrary/webresources/search").queryParam("user", user).path(query);
        System.out.println("Web Target: " + searchwebTarget);
        InputStream is
                = searchwebTarget.request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        SongsXML songs = songxmltoObjects(xml);
       
        
        
        return songs;

    }
    
    public static GenSongsXML getGenServices() throws IOException {
        String query = "generate";
        System.out.println("getGenServices Called");
        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget = searchclient.target("http://localhost:8080/SS_discover/index.html/webresources/discover");
        System.out.println("Web Target: " + searchwebTarget);
        InputStream is
                = searchwebTarget.request(MediaType.APPLICATION_XML).get(InputStream.class);
        
        String xml = IOUtils.toString(is, "utf-8");
        GenSongsXML songs = gensongxmltoObjects(xml);
        System.out.println("Input Stream: " + xml);
        
        System.out.println("Result from getGenServices: " + songs);
        return songs;

    }
    
    private static SongsXML songxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(SongsXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            SongsXML songs = (SongsXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return songs;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static GenSongsXML gensongxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(GenSongsXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            GenSongsXML songs = (GenSongsXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return songs;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
