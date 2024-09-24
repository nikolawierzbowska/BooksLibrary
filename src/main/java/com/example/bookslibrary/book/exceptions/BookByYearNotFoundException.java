package com.example.bookslibrary.book.exceptions;

public class BookByYearNotFoundException extends RuntimeException {
    public BookByYearNotFoundException(String message) {
        super(message);
    }
}
