package com.example.bookslibrary.book;
import com.example.bookslibrary.book.dto.BookDto;
import com.example.bookslibrary.book.dto.UpdatedBookDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

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
    public List<BookDto> getBooksFilterByAuthor(@RequestParam String author) {
        return bookService.getBooksFilterByAuthor(author);
    }

    @GetMapping(params={"year"})
    public List<BookDto> getBooksFilterByYear(@RequestParam int year) {
        return bookService.getBooksFilterByYear(year);
    }

    @GetMapping(params={"rate"})
    public List<BookDto> getBooksFilterByRate(@RequestParam int rate) {
        return bookService.getBooksFilterByRate(rate);
    }

    @PatchMapping("/{bookId}")
    public void updateRate(@PathVariable UUID bookId, @RequestBody UpdatedBookDto updatedBook) {
        bookService.updateRateOfBook(bookId, updatedBook);
    }
}
