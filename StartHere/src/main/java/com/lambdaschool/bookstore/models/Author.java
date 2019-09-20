package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@ApiModel(value = "authors")
@Entity
@Table(name = "authors")

public class Author extends Auditable {
    //author id
    @ApiModelProperty(name = "authorid", value = "Primary key for authors",
            required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    //first name
    @ApiModelProperty(name = "fname", value = "Author's first name",
            required = true, example = "John")
    private String fname;

    //last name
    @ApiModelProperty(name = "lname", value = "Author's last name",
            required = true, example = "Mitchell")
    private String lname;

    //many to many declaration
    @ManyToMany
    @JoinTable(name = "authors",
            joinColumns = {@JoinColumn(name = "authorid")},
            inverseJoinColumns = {@JoinColumn(name = "bookid")}
    )
    @JsonIgnoreProperties("authors")

    private List<Book> books;

    public Author() {
    }

    public Author(String lastname, String firstname) {
        this.lname = lastname;
        this.fname = firstname;
    }


    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
