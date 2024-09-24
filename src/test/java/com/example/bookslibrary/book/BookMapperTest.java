package com.example.bookslibrary.book;

import com.example.bookslibrary.book.dto.BookDto;
import com.example.bookslibrary.book.dto.UpdatedBookDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class BookMapperTest {

    private final BookMapper bookMapper = new BookMapper();

    @Test
    void mapEntityToDto() {
        //given:
        Book book1= new Book("Obcy", "Albert Camus", 1942,"Francja",4);

        //when:
        BookDto actual = bookMapper.mapEntityToDto(book1);

        //then:
        Assertions.assertThat(actual.id()).isEqualTo(book1.getId());
        Assertions.assertThat(actual.title()).isEqualTo(book1.getTitle());
        Assertions.assertThat(actual.author()).isEqualTo(book1.getAuthor());
        Assertions.assertThat(actual.year()).isEqualTo(book1.getYearOfPublication());
        Assertions.assertThat(actual.country()).isEqualTo(book1.getCountry());
        Assertions.assertThat(actual.rate()).isEqualTo(book1.getRate());
    }

    @Test
    void mapDtoToEntity() {
        UpdatedBookDto book1= new UpdatedBookDto(5);

        //when:
        Book actual = bookMapper.mapDtoToEntity(book1);

        //then:
        Assertions.assertThat(actual.getRate()).isEqualTo(5);
    }
}