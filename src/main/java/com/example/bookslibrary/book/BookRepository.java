package com.example.bookslibrary.book;

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

    @Query("SELECT book FROM Book book WHERE book.yearOfPublication= :year")
    List<Book> findByYear(int year);

    @Query("SELECT book FROM Book book WHERE book.rate= :rate")
    List<Book> findByRate(int rate);

    @Query("SELECT book FROM Book book WHERE (book.id= :id)")
    Optional<Book> findById(UUID id);

}
