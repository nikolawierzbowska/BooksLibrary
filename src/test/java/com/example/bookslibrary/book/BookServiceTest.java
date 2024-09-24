package com.example.bookslibrary.book;

import com.example.bookslibrary.book.dto.BookDto;
import com.example.bookslibrary.book.dto.UpdatedBookDto;
import com.example.bookslibrary.book.exceptions.BookByAuthorNotFoundException;
import com.example.bookslibrary.book.exceptions.BookByRateNotFoundException;
import com.example.bookslibrary.book.exceptions.BookByTittleNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookServiceTest {

    private final BookRepository bookRepository = Mockito.mock(BookRepository.class);
    private final BookMapper bookMapper = Mockito.mock(BookMapper.class);
    private final BookService testBookService = new BookService(bookRepository, bookMapper);

    @Test
    void getBooks() {
        //given:
        Book book1 = new Book("Obcy", "Albert Camus", 1942, "Francja", 4);
        Book book2 = new Book("Wielki Gatsby", "Francisa Scotta Fitzgeralda", 1925,
                "USA", null);

        List<Book> books = List.of(book1, book2);

        BookDto bookDto1 = new BookDto(book1.getId(), book1.getTitle(), book1.getAuthor(), book1.getYearOfPublication(),
                book1.getCountry(), book1.getRate());
        BookDto bookDto2 = new BookDto(book2.getId(), book2.getTitle(), book2.getAuthor(), book2.getYearOfPublication(),
                book2.getCountry(), book2.getRate());

        List<BookDto> booksDto = List.of(bookDto1, bookDto2);

        Mockito.when(bookRepository.findAll())
                .thenReturn(books);

        Mockito.when(bookMapper.mapEntityToDto(books.get(0)))
                .thenReturn(booksDto.get(0));
        Mockito.when(bookMapper.mapEntityToDto(books.get(1)))
                .thenReturn(booksDto.get(1));

        //when:
        List<BookDto> actualList = testBookService.getBooks();

        //then:
        Assertions.assertThat(actualList.size()).isEqualTo(2);
        Assertions.assertThat(actualList.contains(bookDto1)).isTrue();
        Assertions.assertThat(actualList.contains(bookDto2)).isTrue();
        Assertions.assertThat(actualList).isEqualTo(booksDto);
    }

    @Test
    void getBooksSortedAscendingByTitle() {
        //given:
        Book book1 = new Book("Obcy", "Albert Camus", 1942, "Francja", 4);
        BookDto bookDto1 = new BookDto(book1.getId(), book1.getTitle(), book1.getAuthor(), book1.getYearOfPublication(),
                book1.getCountry(), book1.getRate());
        Book book2 = new Book("Dżuma", "Albert Camus", 1925, "Francja", 4);
        BookDto bookDto2 = new BookDto(book2.getId(), book2.getTitle(), book2.getAuthor(), book2.getYearOfPublication(),
                book2.getCountry(), book2.getRate());

        List<Book> books = List.of(book1, book2);
        List<BookDto> booksDto = List.of(bookDto2, bookDto1);

        Mockito.when(bookRepository.findAll())
                .thenReturn(books);

        Mockito.when(bookMapper.mapEntityToDto(books.get(0)))
                .thenReturn(booksDto.get(1));
        Mockito.when(bookMapper.mapEntityToDto(books.get(1)))
                .thenReturn(booksDto.get(0));

        //when:
        List<BookDto> actualList = testBookService.getBooksSortedAscendingByTitle();

        //then:
        Assertions.assertThat(actualList.size()).isEqualTo(2);
        Assertions.assertThat(actualList.get(0).title().equals("Dżuma"));
        Assertions.assertThat(actualList.get(1).title().equals("Obcy"));
        Assertions.assertThat(actualList).isEqualTo(booksDto);
    }

    @Test
    void getBooksSortedDescendingByTitle() {
        //given:
        Book book1 = new Book("Dom", "Albert Camus", 1942, "Francja", 4);
        BookDto bookDto1 = new BookDto(book1.getId(), book1.getTitle(), book1.getAuthor(), book1.getYearOfPublication(),
                book1.getCountry(), book1.getRate());
        Book book2 = new Book("Mgła", "Albert Camus", 1925, "Francja", 4);
        BookDto bookDto2 = new BookDto(book2.getId(), book2.getTitle(), book2.getAuthor(), book2.getYearOfPublication(),
                book2.getCountry(), book2.getRate());

        List<Book> books = List.of(book1, book2);
        List<BookDto> booksDto = List.of(bookDto2, bookDto1);

        Mockito.when(bookRepository.findAll())
                .thenReturn(books);

        Mockito.when(bookMapper.mapEntityToDto(books.get(0)))
                .thenReturn(booksDto.get(1));
        Mockito.when(bookMapper.mapEntityToDto(books.get(1)))
                .thenReturn(booksDto.get(0));

        //when:
        List<BookDto> actualList = testBookService.getBooksSortedDescendingByTitle();

        //then:
        Assertions.assertThat(actualList.size()).isEqualTo(2);
        Assertions.assertThat(actualList.get(0).title().equals("Mgła"));
        Assertions.assertThat(actualList.get(1).title().equals("Dom"));
        Assertions.assertThat(actualList).isEqualTo(booksDto);

    }

    @Test
    void getBookFilterByTitle() {
        //given:
        Book book1 = new Book("Obcy", "Albert Camus", 1942, "Francja", 4);
        BookDto bookDto1 = new BookDto(book1.getId(), book1.getTitle(), book1.getAuthor(), book1.getYearOfPublication(),
                book1.getCountry(), book1.getRate());

        Mockito.when(bookRepository.findByTitle(book1.getTitle().toLowerCase()))
                .thenReturn(Optional.of(book1));

        Mockito.when(bookMapper.mapEntityToDto(book1))
                .thenReturn(bookDto1);

        //when:
        BookDto actual = testBookService.getBookFilterByTitle(book1.getTitle().toLowerCase());

        //then:
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.id()).isEqualTo(bookDto1.id());
        Assertions.assertThat(actual.author()).isEqualTo(bookDto1.author());
        Assertions.assertThat(actual.country()).isEqualTo(bookDto1.country());
        Assertions.assertThat(actual.rate()).isEqualTo(bookDto1.rate());
    }

    @Test
    void getBooksFilterByAuthor() {
        //given:
        Book book1 = new Book("Obcy", "Albert Camus", 1942, "Francja", 4);
        BookDto bookDto1 = new BookDto(book1.getId(), book1.getTitle(), book1.getAuthor(), book1.getYearOfPublication(),
                book1.getCountry(), book1.getRate());
        Book book2 = new Book("Dżuma", "Albert Camus", 1925, "Francja", 4);
        BookDto bookDto2 = new BookDto(book2.getId(), book2.getTitle(), book2.getAuthor(), book2.getYearOfPublication(),
                book2.getCountry(), book2.getRate());

        List<Book> books = List.of(book1, book2);
        List<BookDto> booksDto = List.of(bookDto1, bookDto2);

        Mockito.when(bookRepository.findByAuthor(book1.getAuthor().toLowerCase()))
                .thenReturn(books);

        Mockito.when(bookMapper.mapEntityToDto(books.get(0)))
                .thenReturn(booksDto.get(0));
        Mockito.when(bookMapper.mapEntityToDto(books.get(1)))
                .thenReturn(booksDto.get(1));

        //when:
        List<BookDto> actualList = testBookService.getBooksFilterByAuthor(book1.getAuthor().toLowerCase());

        //then:
        Assertions.assertThat(actualList.size()).isEqualTo(2);
        Assertions.assertThat(actualList.contains(bookDto1)).isTrue();
        Assertions.assertThat(actualList.contains(bookDto2)).isTrue();
        Assertions.assertThat(actualList).isEqualTo(booksDto);
    }

    @Test
    void getBooksFilterByYear() {
        //given:
        Book book1 = new Book("Obcy", "Albert Camus", 1942, "Francja", 4);
        BookDto bookDto1 = new BookDto(book1.getId(), book1.getTitle(), book1.getAuthor(), book1.getYearOfPublication(),
                book1.getCountry(), book1.getRate());
        Book book2 = new Book("Dżuma", "Albert Camus", 1942, "Francja", 5);
        BookDto bookDto2 = new BookDto(book2.getId(), book2.getTitle(), book2.getAuthor(), book2.getYearOfPublication(),
                book2.getCountry(), book2.getRate());

        List<Book> books = List.of(book1, book2);
        List<BookDto> booksDto = List.of(bookDto1, bookDto2);

        Mockito.when(bookRepository.findByYear(book1.getYearOfPublication()))
                .thenReturn(books);

        Mockito.when(bookMapper.mapEntityToDto(books.get(0)))
                .thenReturn(booksDto.get(0));
        Mockito.when(bookMapper.mapEntityToDto(books.get(1)))
                .thenReturn(booksDto.get(1));

        //when:
        List<BookDto> actualList = testBookService.getBooksFilterByYear(book1.getYearOfPublication());

        //then:
        Assertions.assertThat(actualList.size()).isEqualTo(2);
        Assertions.assertThat(actualList.contains(bookDto1)).isTrue();
        Assertions.assertThat(actualList.contains(bookDto2)).isTrue();
        Assertions.assertThat(actualList).isEqualTo(booksDto);
    }

    @Test
    void getBooksFilterByRate() {
        //given:
        Book book1 = new Book("Obcy", "Albert Camus", 1942, "Francja", 4);
        BookDto bookDto1 = new BookDto(book1.getId(), book1.getTitle(), book1.getAuthor(), book1.getYearOfPublication(),
                book1.getCountry(), book1.getRate());
        Book book2 = new Book("Dżuma", "Albert Camus", 1925, "Francja", 4);
        BookDto bookDto2 = new BookDto(book2.getId(), book2.getTitle(), book2.getAuthor(), book2.getYearOfPublication(),
                book2.getCountry(), book2.getRate());

        List<Book> books = List.of(book1, book2);
        List<BookDto> booksDto = List.of(bookDto1, bookDto2);

        Mockito.when(bookRepository.findByRate(book1.getRate()))
                .thenReturn(books);

        Mockito.when(bookMapper.mapEntityToDto(books.get(0)))
                .thenReturn(booksDto.get(0));
        Mockito.when(bookMapper.mapEntityToDto(books.get(1)))
                .thenReturn(booksDto.get(1));

        //when:
        List<BookDto> actualList = testBookService.getBooksFilterByRate(book1.getRate());

        //then:
        Assertions.assertThat(actualList.size()).isEqualTo(2);
        Assertions.assertThat(actualList.contains(bookDto1)).isTrue();
        Assertions.assertThat(actualList.contains(bookDto2)).isTrue();
        Assertions.assertThat(actualList).isEqualTo(booksDto);
    }

    @Captor
    private ArgumentCaptor<Book> roleArgumentCaptor;

    @Test
    void updateRateOfBook() {
        //given:
        Book book = new Book();
        UUID id = UUID.fromString("b8715a07-959a-4326-afff-0cfc74a6e85b");
        UpdatedBookDto updatedBookDto = new UpdatedBookDto(5);

        Mockito.when(bookRepository.findById(id))
                .thenReturn(Optional.of(book));

        Mockito.when(bookMapper.mapDtoToEntity(updatedBookDto))
                .thenReturn(book);

        //when:
        testBookService.updateRateOfBook(id, updatedBookDto);

        //then:
        Mockito.verify(bookRepository).save(roleArgumentCaptor.capture());
    }

    @Test
    void getBookByTittleNotFoundException() {
        //given:
        String title = "Obcy";
        Mockito.when(bookRepository.findByTitle(title))
                .thenReturn(Optional.empty());

        //when:
        Throwable throwable = Assertions.catchThrowable(() ->
                testBookService.getBookFilterByTitle(title));

        //then:
        Assertions.assertThat(throwable).isInstanceOf(BookByTittleNotFoundException.class);
        Assertions.assertThat(throwable).hasMessage("Not found the book with that title: " + title);
    }

    @Test
    void addRateNotFoundException() {
        //given:
        int rate = 6;
        Mockito.when(bookRepository.findByRate(rate))
                .thenReturn(Collections.emptyList());

        //when:
        Throwable throwable = Assertions.catchThrowable(() ->
                testBookService.getBooksFilterByRate(rate));

        //then:
        Assertions.assertThat(throwable).isInstanceOf(BookByRateNotFoundException.class);
        Assertions.assertThat(throwable).hasMessage("The range of rate is from 1 to 5. Put the correct rate!");
    }

    @Test
    void getBookByAuthorNotFoundException() {
        //given:
        String author = "Camus";
        Mockito.when(bookRepository.findByAuthor(author))
                .thenReturn(Collections.emptyList());

        //when:
        Throwable throwable = Assertions.catchThrowable(() ->
                testBookService.getBooksFilterByAuthor(author));

        //then:
        Assertions.assertThat(throwable).isInstanceOf(BookByAuthorNotFoundException.class);
        Assertions.assertThat(throwable).hasMessage("Not found the books had been written by: " + author);
    }
}