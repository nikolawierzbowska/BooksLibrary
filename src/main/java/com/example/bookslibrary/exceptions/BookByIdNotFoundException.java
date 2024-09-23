package com.example.bookslibrary.exceptions;

public class BookByIdNotFoundException extends RuntimeException {
    public BookByIdNotFoundException(String message) {
        super(message);
    }
}
