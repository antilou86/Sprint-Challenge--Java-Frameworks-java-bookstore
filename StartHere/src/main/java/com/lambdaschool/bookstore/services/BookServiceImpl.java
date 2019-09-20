package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepo;

    @Override
    public List<Book> findAll(Pageable pageble) {
        List<Book> ret=new ArrayList<>();
        bookRepo.findAll(pageble).iterator().forEachRemaining(ret::add);
        return ret;
    }

    @Override
    public List<Book> findAll() {
        List<Book> ret=new ArrayList<>();
        bookRepo.findAll().iterator().forEachRemaining(ret::add);
        return ret;
    }

    @Override
    public Book findBookById(long id) {
        return bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(Long.toString(id)));
    }

    @Transactional
    @Override
    public Book save(Book book) {
        Book data=new Book();

        if (book.getBooktitle()!=null){
            data.setBooktitle(book.getBooktitle());
        }
        if(book.getISBN()!=null){
            data.setISBN(book.getISBN());
        }
        data.setCopy(book.getCopy());

        if(book.getAuthors().size()>0){
            for (Author b : book.getAuthors()){
                data.getAuthors().add(b);
            }
        }

        return bookRepo.save(data);
    }

    @Transactional
    @Override
    public Book update(Book book, long id) {
        Book copy=bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(Long.toString(id)));

        if (book.getBooktitle()!=null){
            copy.setBooktitle(book.getBooktitle());
        }
        if(book.getISBN()!=null){
            copy.setISBN(book.getISBN());
        }
        copy.setCopy(book.getCopy());

        if(book.getAuthors().size()>0){
            for (Author b : book.getAuthors()){
                copy.getAuthors().add(b);
            }
        }

        return bookRepo.save(copy);
    }

    @Override
    public void delete(long id) {
        if(bookRepo.findById(id).isPresent())
            bookRepo.deleteById(id);
        else
            throw new ResourceNotFoundException(Long.toString(id));
    }
}