package com.example.bookslibrary.exceptions;

public class BookByTittleNotFoundException extends RuntimeException {
    public BookByTittleNotFoundException(String message) {
        super(message);
    }
}
