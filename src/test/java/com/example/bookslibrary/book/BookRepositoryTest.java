package com.example.bookslibrary.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository testBookRepository;

    @Test
    void findAll() {
    }

    @Test
    void findByTitle() {
    }

    @Test
    void findByAuthor() {
    }

    @Test
    void findByYear() {
    }

    @Test
    void findByRate() {
    }

    @Test
    void findById() {
    }
}