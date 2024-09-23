package com.example.bookslibrary;

import com.example.bookslibrary.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto mapEntityToDto(Book book){
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(),book.getYear() , book.getCountry(),
                book.getRate());
    }

}
