package com.example.bookslibrary.exceptions;

public class BookByRateNotFoundException extends RuntimeException {
    public BookByRateNotFoundException(String message) {
        super(message);
    }
}
