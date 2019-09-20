package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@ApiModel(value = "books")
@Entity
@Table(name="books")

public class Book extends Auditable {

    //book primary key-id
    @ApiModelProperty(name = "bookid", value = "Primary key for books",
            required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    //book title
    @ApiModelProperty(name = "title", value = "Title of book",
            required = true, example = "The Lion the Witch and the Wardrobe")
    private String booktitle;

    //book ISBN
    @ApiModelProperty(name = "ISBN", value = "Book's ISBN number",
            required = true, example = "9780738206752")
    private String ISBN;

    //book copyright data
    @ApiModelProperty(name = "copy", value = "Year the book was published",
            required = false, example = "2007")
    private int copy;

    //many to many declaration
    @ManyToMany(mappedBy = "books")
    @JoinTable( name="wrote",
            joinColumns = {@JoinColumn(name="bookid")},
            inverseJoinColumns = {@JoinColumn(name = "authorid")}
    )
    @JsonIgnoreProperties("books")
    
    private List<Author> authors;

    //default constructor
    public Book() {
    }
    //constructor with params
    public Book(String booktitle, String ISBN, int copy) {
        this.booktitle = booktitle;
        this.ISBN = ISBN;
        this.copy = copy;
    }

    //getters and setters
    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}