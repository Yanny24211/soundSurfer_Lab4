/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ryerson.ca.discover.business;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

//need to import
import ryerson.ca.frontend.helper;
/**
 *
 * @author deeps
 */
public class Generate {
    public Generate() {
     User user = (User) session.getAttribute("user");
        if(user ==null){
            response.sendRedirect("index.jsp");
        }
        java.util.Random random = new java.util.Random();
        
        String filePath = "C:\\Users\\deeps\\Desktop\\coe692_term_project\\soundSurfer_Lab4\\SS_discover\\tracks.txt";

        ArrayList<String> trackIds = new ArrayList<>();
        trackIds.add("");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                trackIds.add(line);
            }
        } catch (IOException e) {System.out.println(e);}
        int randomValue = random.nextInt(trackIds.size()); 
        
        String trackId = trackIds.get(randomValue);
        request.setAttribute("trackId", trackId);
        System.out.println(trackId);       
    }
}
