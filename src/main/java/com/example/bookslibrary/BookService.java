package com.example.bookslibrary;

import com.example.bookslibrary.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final  BookMapper bookMapper;

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
}
