/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.frontend.helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

 @XmlRootElement(name = "books")
@XmlAccessorType (XmlAccessType.FIELD)
       public class BooksXML{
     @XmlElement(name="book")
           private ArrayList<Book> books;
           
           
           public List<Book>getBooks(){
               return books;
               
           }
           BooksXML(){
               
               
           }
           public void setBook(ArrayList<Book> bs){
               books=bs;
               
           }
           
       }