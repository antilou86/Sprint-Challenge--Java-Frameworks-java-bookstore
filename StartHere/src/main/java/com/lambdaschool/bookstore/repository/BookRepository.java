package com.lambdaschool.bookstore.repository;

import com.lambdaschool.bookstore.models.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    @Modifying
    @Query("INSERT INTO wrote (bookid, authorid) VALUES (:bookid, :authorid)", nativeQuery = true)
        public void assignBookToAuthor(Long bookid, Long authorid);

}
