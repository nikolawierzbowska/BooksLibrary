package com.example.bookslibrary.book.exceptions;

public class BookByRateNotFoundException extends RuntimeException {
    public BookByRateNotFoundException(String message) {
        super(message);
    }
}
