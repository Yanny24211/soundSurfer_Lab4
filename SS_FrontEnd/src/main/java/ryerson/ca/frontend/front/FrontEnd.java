/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.frontend.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.NewCookie;
import ryerson.ca.frontend.helper.Song;
import ryerson.ca.frontend.helper.SongsXML;
import ryerson.ca.frontend.helper.User; 
import ryerson.ca.frontend.business.Business; 
import ryerson.ca.frontend.helper.GenSongsXML;


/**
 *
 * @author student
 */
@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {
    
    Authenticate autho;

    public FrontEnd() {
        autho = new Authenticate();
    }
    private final String authenticationCookieName = "login_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        
        System.out.println("TOKEN IS");
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty())
           try {
            if (this.autho.verify(token).getKey()) {
                  Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>
                             (token,this.autho.verify(token).getValue());
            return entry;

            } else {
                 Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
        }

       Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String token = isAuthenticated(request).getKey();
        //String uname = isAuthenticated(request).getValue();
        String hiddenParam = request.getParameter("pageName");
        String username; 
        String password;
        PrintWriter out = response.getWriter(); 
        switch (hiddenParam) {
            case "login":
                username = request.getParameter("username");
                password = request.getParameter("password");
                boolean isAuthenticated = Business.validate(username, password);
                if (isAuthenticated) {
                    token = autho.createJWT("FrontEnd", username, 10000000);
                    session.setAttribute("username", username);
                    session.setAttribute("token", token);
         
                    Cookie newCookie = new Cookie(authenticationCookieName, token);
                    System.out.println("Cookie" + newCookie); 
                    response.addCookie(newCookie);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("homepage.jsp");

                    requestDispatcher.forward(request, response);

                }
                else{
                    out.println("Login Attempt Unsuccessful, Please Try Again");
                    response.sendRedirect("index.html");
                }
                break;
            case "register":
                username = request.getParameter("username");
                password = request.getParameter("password");
                boolean exists = (Business.getUser(username) == null);
                if (exists) {
                    User user = new User(username, password); 
                    Business.addUser(user); 
                    out.println("Registration Successful, Redirecting to Login Page!");
                    response.sendRedirect("index.html");
         
                }
                else{out.println("Username already taken. Choose a different username.");}
                break;
            case "logout":
                session = request.getSession(); 
                System.out.println("Sesson:" + session);
                session.removeAttribute("username");
                session.invalidate(); 
                response.sendRedirect("index.html");
                break; 
            case "goDiscover":
                System.out.println("goDiscover Called");
                GenSongsXML genResult; 
                genResult = retreiveGenServicesFromBackend();
                System.out.println("Services retrieved");
                request.setAttribute("genSongResults", genResult);
                System.out.println("genSongResults: " + genResult);
                RequestDispatcher rdd = request.getRequestDispatcher("discover.jsp");
                rdd.forward(request, response);
                break; 
                
            case "goTrackLib": 
                RequestDispatcher rd = request.getRequestDispatcher("library.jsp");
                rd.forward(request, response); 
                break;
            case "search":
                SongsXML result;
                String user = (String) request.getSession().getAttribute("username"); 
                System.out.println("THE AQUIRED USERNAME>> " + user);
                String query = request.getParameter("query");
                
                //request.setAttribute("username", uname);
                result = retreiveServicesFromBackend(query, token, user);

                request.setAttribute("songResults", result);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("librarySearch.jsp");
                requestDispatcher.forward(request, response);
                break;
        }

        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Process Redirector";
    }// </editor-fold>

    private SongsXML retreiveServicesFromBackend(String query, String token, String user) {
        try {
            return (Business.getServices(query, token, user));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }
    
    private GenSongsXML retreiveGenServicesFromBackend() {
        try {
            return (Business.getGenServices());
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }
}
