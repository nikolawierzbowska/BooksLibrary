package com.example.bookslibrary.book.exceptions;

public class BookByAuthorNotFoundException extends RuntimeException {
    public BookByAuthorNotFoundException(String message) {
        super(message);
    }
}
