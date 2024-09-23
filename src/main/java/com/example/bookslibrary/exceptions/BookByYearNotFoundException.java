package com.example.bookslibrary.exceptions;

public class BookByYearNotFoundException extends RuntimeException {
    public BookByYearNotFoundException(String message) {
        super(message);
    }
}
