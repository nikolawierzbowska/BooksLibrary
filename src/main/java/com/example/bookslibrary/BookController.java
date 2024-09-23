package com.example.bookslibrary;
import com.example.bookslibrary.dto.BookDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<BookDto> getBooks(){
        return  bookService.getBooks();
    }
}
