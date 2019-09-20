package com.lambdaschool.bookstore.controllers;
import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.ErrorDetail;
import com.lambdaschool.bookstore.services.AuthorService;
import com.lambdaschool.bookstore.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @ApiOperation(value = "Updates book id with body book", consumes = "Book",response=void.class)
    @ApiResponses(value = {
            @ApiResponse(code=404,message = "Book Not Found",response = ErrorDetail.class),
            @ApiResponse(code=500, message="Error", response = ErrorDetail.class)
    })
    @PutMapping(value = "/books/{id}",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<?> updateBook(@PathVariable long id, @RequestBody @Valid Book book){
        Book res = bookService.update(book,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Create a book")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Error Creating Book", response = ErrorDetail.class),
            @ApiResponse(code=201,message = "Book Created", response=void.class)
    })
    @PostMapping(value = "/books/{bookid}/authors/{authorid}")
    public ResponseEntity<?> updateAuthor(@PathVariable long bookid, @PathVariable long authorid){
        Author author=authorService.findAuthorById(authorid);
        Book book = bookService.findBookById(bookid);

        book.getAuthors().add(author);
        book=bookService.save(book);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a book")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Error Creating Book", response = ErrorDetail.class),
            @ApiResponse(code=404,message="Book Not Found", response = ErrorDetail.class)
    })
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id){
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
