package com.example.bookslibrary;

import com.example.bookslibrary.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final  BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream()
                .map(book -> bookMapper.mapEntityToDto(book))
                .toList();


    }
}
