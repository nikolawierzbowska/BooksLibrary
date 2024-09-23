package com.example.bookslibrary;

import com.example.bookslibrary.dto.BookDto;
import com.example.bookslibrary.exceptions.BookByAuthorNotFoundException;
import com.example.bookslibrary.exceptions.BookByTittleNotFoundException;
import com.example.bookslibrary.exceptions.BookByYearNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> bookMapper.mapEntityToDto(book))
                .toList();
    }

    public List<BookDto> getBooksSortedAscendingByTitle() {
        return bookRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .map(bookMapper::mapEntityToDto)
                .toList();
    }

    public List<BookDto> getBooksSortedDescendingByTitle() {
        return bookRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Book::getTitle).reversed())
                .map(bookMapper::mapEntityToDto)
                .toList();
    }

    public BookDto getBookFilterByTitle(String title) {
        return bookRepository.findByTitle(title.toLowerCase())
                .map(book -> bookMapper.mapEntityToDto(book))
                .orElseThrow(() -> getBookByTittleNotFoundException(title));
    }

    public List<BookDto> getBooksFilterByAuthor(String author) {;
        List<BookDto> listBooks = bookRepository.findByAuthor(author.toLowerCase()).stream()
                .map(book -> bookMapper.mapEntityToDto(book))
                .toList();
        if(listBooks.isEmpty()){
            throw new BookByAuthorNotFoundException("Not found the books had been written by: " +author);
        }
        return listBooks;
    }

    public List<BookDto> getBooksFilterByYear(int year) {
        List<BookDto> listBooks = bookRepository.findByYear(year)
                .stream()
                .map(book -> bookMapper.mapEntityToDto(book))
                .toList();
        if(listBooks.isEmpty()){
            throw new BookByYearNotFoundException("Not found the book had been written in: " + year);
        }
        return listBooks;
    }



    public BookByTittleNotFoundException getBookByTittleNotFoundException(String title) {
        return new BookByTittleNotFoundException("Not found the book with that title: " + title);
    }

    public BookByAuthorNotFoundException getBookByAuthorNotFoundException(String author) {
        return new BookByAuthorNotFoundException("Not fount the book with that author: " + author);
    }


}



