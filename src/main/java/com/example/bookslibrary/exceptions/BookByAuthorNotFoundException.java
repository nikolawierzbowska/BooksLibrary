package com.example.bookslibrary.exceptions;

public class BookByAuthorNotFoundException extends RuntimeException {
    public BookByAuthorNotFoundException(String message) {
        super(message);
    }
}
