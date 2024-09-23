package com.example.bookslibrary;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository  extends JpaRepository<Book, UUID>{
    List<Book> findAll();

    @Query("SELECT book FROM Book book WHERE LOWER(book.title)= LOWER(:title)")
    Optional<Book> findByTitle(String title);

    @Query("SELECT book FROM Book book WHERE LOWER(book.author)= LOWER(:author)")
    List<Book> findByAuthor(String author);

    @Query("SELECT book FROM Book book WHERE book.year= :year")
    List<Book> findByYear(int year);
}
