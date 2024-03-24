/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ryerson.ca.tracklibrary.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author yannypatel
 */
public class Song_CRUD {
    private static Connection getCon(){
    Connection con=null;
     try{
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/search_LBS?autoReconnect=true&useSSL=false", "root", "student");
         System.out.println("Connection established.");
     }
     catch(Exception e){ System.out.println(e);}
     return con;
    }
    
    public static Set<Book> searchForBooks(String query){
        Set<Book> books= new HashSet<Book>();
        try{
            Connection con= getCon();
            
            String q = "select * from BOOK NATURAL JOIN BOOK_AUTHOR "
                    + "NATURAL JOIN AUTHOR WHERE title LIKE '%"+query+"%'"
                    + " OR firstName LIKE '%"+
                    query+"%' OR lastName LIKE '%"+
                    query+"%';";
                        System.out.println(q);
			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				//been= new UserInfo();
				String isbn=rs.getString("isbn");
				String title=rs.getString("title");
                                String barcode=rs.getString("barcode");
                                String firstname=rs.getString("firstName");
                                String lastname=rs.getString("lastName");
                                Author author= new Author(firstname, lastname);
                           
                                Book book = new Book(isbn,title,barcode, author);
                                books.add(book);
                                
                                }
			
			con.close();

		}catch(Exception e){System.out.println(e);}
            
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+books.size());
        return books;
        
    }
}
