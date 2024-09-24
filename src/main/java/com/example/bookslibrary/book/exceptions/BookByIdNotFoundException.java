package com.example.bookslibrary.book.exceptions;

public class BookByIdNotFoundException extends RuntimeException {
    public BookByIdNotFoundException(String message) {
        super(message);
    }
}
