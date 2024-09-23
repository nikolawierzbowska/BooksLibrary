package com.example.bookslibrary;
import com.example.bookslibrary.dto.BookDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<BookDto> getBooks(){
        return  bookService.getBooks();
    }

    @GetMapping(params={"titleSort"})
    public List<BookDto> getBooksSortedByTitle(@RequestParam String titleSort) {
            if (titleSort.equals("ASC".toUpperCase())) {
                return bookService.getBooksSortedAscendingByTitle();
            } else if (titleSort.equals("DSC".toUpperCase())) {
                return bookService.getBooksSortedDescendingByTitle();
            }
            return getBooks();
    }

    @GetMapping(params={"title"})
    public BookDto getBookFilterByTitle(@RequestParam String title) {
        return bookService.getBookFilterByTitle(title);
    }

    @GetMapping(params={"author"})
    public List<BookDto> getBookFilterByAuthor(@RequestParam String author) {
        return bookService.getBooksFilterByAuthor(author);
    }

    @GetMapping(params={"year"})
    public List<BookDto> getBookFilterByYear(@RequestParam int year) {
        return bookService.getBooksFilterByYear(year);
    }
}
