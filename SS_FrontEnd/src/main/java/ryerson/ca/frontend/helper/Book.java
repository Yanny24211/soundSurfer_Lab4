/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.frontend.helper;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author student
 */
    @XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

 @XmlTransient
    private boolean tobeHold;

    public boolean isTobeHold() {
        return tobeHold;
    }

    public void setTobeHold(boolean tobeHold) {
        this.tobeHold = tobeHold;
    }
    private String isbn; 
    private String title;
    private String barcode;
    ArrayList<Author> author ;

    public Book(){
        
    }
    public Book(String isbn, String title, String barcode, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.barcode = barcode;
        this.author= new ArrayList<Author>();
        this.author.add(new Author(author.getFirstName(), author.getLastName()));
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getBarcode() {
        return barcode;
    }
    public ArrayList<Author> getAuthors() {
        return author;
    }
     public void addAuthor(ArrayList<Author> authors) {
         for(Author a:authors){
        this.author.add(new Author(a.getFirstName(),a.getLastName()));
         }
    }
}
