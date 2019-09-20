package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.services.AuthorService;
import com.lambdaschool.bookstore.services.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RootController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @ApiOperation(value = "Returns all books",responseContainer = "List")
    @GetMapping(value = "/books",
            produces={"application/json"})
    public ResponseEntity<?> allBooks(){
        List<Book> res=bookService.findAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns all authors",responseContainer = "List")
    @GetMapping(value = "/authors",
            produces = {"application/json"})
    public ResponseEntity<?> allAuthors(){
        List<Author> res=authorService.findAll();
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}