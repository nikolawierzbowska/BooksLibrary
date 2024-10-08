package com.example.bookslibrary.book;

import com.example.bookslibrary.book.dto.BookDto;
import com.example.bookslibrary.book.dto.UpdatedBookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto mapEntityToDto(Book book){
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(),book.getYearOfPublication() , book.getCountry(),
                book.getRate());
    }

    public Book mapDtoToEntity(UpdatedBookDto book){
        return new Book(book.getRate());
    }

}
