package com.example.bookslibrary.book.exceptions;

public class BookByTittleNotFoundException extends RuntimeException {
    public BookByTittleNotFoundException(String message) {
        super(message);
    }
}
