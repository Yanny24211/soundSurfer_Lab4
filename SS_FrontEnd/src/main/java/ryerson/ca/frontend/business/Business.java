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
import ryerson.ca.frontend.helper.Song;
import ryerson.ca.frontend.helper.SongsXML;
import ryerson.ca.frontend.helper.User; 

public class Business {
    
    private static Connection getCon(){
        Connection con = null; 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account?autoReconnect=true&useSSL=false", "root", "student");
            System.out.println("Connection Established");
        }
        catch(Exception e){System.out.println("Connection Failed: " + e);} 
        return con;
    }
    
    public static void addUser(User user){
        String uname = user.getUsername(); 
        String pass = user.getPassword(); 
        try{
            
            Connection con; 
            con = getCon();
            String userQ = "insert into theUser(user_id, username, date_of_birth, password) values (null, \"" + uname + "\", null, \"" + pass + "\")"; 
            PreparedStatement  ps = con.prepareStatement(userQ); 
            System.out.print(userQ);
            int rowsAffected = ps.executeUpdate();
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
            Connection con = getCon(); 
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



    public static SongsXML getServices(String query, String token) throws IOException {
        
        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget
                = searchclient.target("http://localhost:8080/SS_trackLibrary/webresources/search");
        InputStream is
                = searchwebTarget.path(query).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        SongsXML songs = songxmltoObjects(xml);
       

        return (songs);

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

}
