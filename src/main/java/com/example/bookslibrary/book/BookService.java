package com.example.bookslibrary.book;

import com.example.bookslibrary.book.dto.BookDto;
import com.example.bookslibrary.book.dto.UpdatedBookDto;
import com.example.bookslibrary.book.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

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

    public List<BookDto> getBooksFilterByAuthor(String author) {
        List<BookDto> listBooks = bookRepository.findByAuthor(author.toLowerCase()).stream()
                .map(book -> bookMapper.mapEntityToDto(book))
                .toList();
        if(listBooks.isEmpty()){
            throw new BookByAuthorNotFoundException("Not found the books had been written by: " +author);
        }
        return listBooks;
    }

    public List<BookDto> getBooksFilterByYear(int year) {
        if(year<1000 || year>2024){
            throw new BookByYearNotFoundException("The year is not correct. The range of year is from 1000 to 2024! ");
        }
        List<BookDto> listBooks = bookRepository.findByYear(year)
                .stream()
                .map(book -> bookMapper.mapEntityToDto(book))
                .toList();
        if(listBooks.isEmpty()){
            throw new BookByYearNotFoundException("Not found the book had been written in: " + year);
        }
        return listBooks;
    }

    public List<BookDto> getBooksFilterByRate(int rate) {
        addRateNotFoundException(rate);
        List<BookDto> listBooks =  bookRepository.findByRate(rate)
                .stream()
                .map(book -> bookMapper.mapEntityToDto(book))
                .toList();
        if(listBooks.isEmpty()){
            throw new BookByRateNotFoundException("Not found the book with rate: " + rate);
        }
        return listBooks;
    }

    public void updateRateOfBook(UUID bookId, UpdatedBookDto updateBook) {
        addRateNotFoundException(updateBook.getRate());
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->getBookByAuthorNotFoundException(bookId));

        Book updatedBook = bookMapper.mapDtoToEntity(updateBook);
        book.setRate(updatedBook.getRate());
        bookRepository.save(book);

    }

    public BookByTittleNotFoundException getBookByTittleNotFoundException(String title) {
        return new BookByTittleNotFoundException("Not found the book with that title: " + title);
    }

    public void addRateNotFoundException(int rate) {
        if(rate<1 || rate>5){
            throw new BookByRateNotFoundException("The range of rate is from 1 to 5. Put the correct rate!");
        }
    }

    public BookByIdNotFoundException getBookByAuthorNotFoundException(UUID id) {
        return new BookByIdNotFoundException("Not fount the book with that id: " + id);
    }


}



