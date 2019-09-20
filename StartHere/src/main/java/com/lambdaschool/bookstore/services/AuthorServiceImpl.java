package com.lambdaschool.bookstore.services;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorrepo;

    @Override
    public List<Author> findAll() {
        List<Author> temp = new ArrayList<>();
        authorrepo.findAll().iterator().forEachRemaining(temp::add);
        return temp;
    }

    @Override
    public Author findAuthorById(long id) {
        return authorrepo.findById(id).orElseThrow(()->new ResourceNotFoundException(Long.toString(id)));
    }
}