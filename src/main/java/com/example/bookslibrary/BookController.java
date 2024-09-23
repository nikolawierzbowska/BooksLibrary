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

    @GetMapping(params={"title"})
    public List<BookDto> getBooksSortByTitle(@RequestParam String title) {
            if (title.equals("ASC".toUpperCase())) {
                return bookService.getBooksSortedAscendingByTitle();
            } else if (title.equals("DSC".toUpperCase())) {
                return bookService.getBooksSortedDescendingByTitle();
            }
        return getBooks();
    }
}
