package com.example.bookslibrary.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository testBookRepository;

    @Test
    void findAll() {
        Book book1= new Book("Obcy", "Albert Camus", 1942,"Francja",4);
        Book book2= new Book("Obcy2", "Albert Nowak", 1902,"Polska",null);
        testBookRepository.save(book1);
        testBookRepository.save(book2);
        //when:
        List<Book> actual = testBookRepository.findAll();

        //then:
        Assertions.assertThat(actual.get(0).getTitle()).isEqualTo(book1.getTitle());
        Assertions.assertThat(actual.get(1).getTitle()).isEqualTo(book2.getTitle());

        Assertions.assertThat(actual.get(0).getAuthor()).isEqualTo(book1.getAuthor());
        Assertions.assertThat(actual.get(1).getAuthor()).isEqualTo(book2.getAuthor());

        Assertions.assertThat(actual.get(0).getYearOfPublication()).isEqualTo(book1.getYearOfPublication());
        Assertions.assertThat(actual.get(1).getYearOfPublication()).isEqualTo(book2.getYearOfPublication());

        Assertions.assertThat(actual.get(0).getCountry()).isEqualTo(book1.getCountry());
        Assertions.assertThat(actual.get(1).getCountry()).isEqualTo(book2.getCountry());

        Assertions.assertThat(actual.get(0).getRate()).isEqualTo(book1.getRate());
        Assertions.assertThat(actual.get(1).getRate()).isEqualTo(book2.getRate());
        Assertions.assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void findByTitle() {
        //given:
        String title = "Obcy";
        UUID id1 = UUID.fromString("b8715a07-959a-4326-afff-0cfc74a6e85b");;

        Book book1 = new Book();
        book1.setId(id1);
        book1.setTitle(title);
        book1.setAuthor("Albert Camus");
        book1.setYearOfPublication(1925);
        book1.setCountry("Francja");
        book1.setRate(5);

        testBookRepository.save(book1);

        //when:
        Optional<Book> actual = testBookRepository.findByTitle(title);

        //then:
        Assertions.assertThat(actual.get().getTitle()).isEqualTo(title);
        Assertions.assertThat(actual.get().getTitle()).isEqualTo(title);


    }

    @Test
    void findByAuthor() {
        //given:
        String author = "Albert Camus";
        UUID id1 = UUID.fromString("b8715a07-959a-4326-afff-0cfc74a6e85b");
        UUID id2 = UUID.fromString("a8715a07-959a-4326-afff-0cfc74a6e85b");

        Book book1 = new Book();
        book1.setId(id1);
        book1.setTitle("Obcy");
        book1.setAuthor("Albert Camus");
        book1.setYearOfPublication(1925);
        book1.setCountry("Francja");
        book1.setRate(2);

        Book book2 = new Book();
        book2.setId(id2);
        book2.setTitle("Kamizelka");
        book2.setAuthor("Albert Camus");
        book2.setYearOfPublication(1930);
        book2.setCountry("Francja");
        book2.setRate(4);
        testBookRepository.save(book1);
        testBookRepository.save(book2);
        //when:
        List<Book> actual = testBookRepository.findByAuthor(author);

        //then:
        Assertions.assertThat(actual.get(0).getAuthor()).isEqualTo(author);
        Assertions.assertThat(actual.get(1).getAuthor()).isEqualTo(author);


}

    @Test
    void findByYear() {
        //given:
        int year = 1922;
        UUID id1 = UUID.fromString("b8715a07-959a-4326-afff-0cfc74a6e85b");
        UUID id2 = UUID.fromString("a8715a07-959a-4326-afff-0cfc74a6e85b");

        Book book1 = new Book();
        book1.setId(id1);
        book1.setTitle("Obcy");
        book1.setAuthor("Albert Camus");
        book1.setYearOfPublication(year);
        book1.setCountry("Francja");
        book1.setRate(2);

        Book book2 = new Book();
        book2.setId(id2);
        book2.setTitle("Kamizelka");
        book2.setAuthor("Albert Camus");
        book2.setYearOfPublication(year);
        book2.setCountry("Francja");
        book2.setRate(4);
        testBookRepository.save(book1);
        testBookRepository.save(book2);
        //when:
        List<Book> actual = testBookRepository.findByYear(year);

        //then:
        Assertions.assertThat(actual.get(0).getYearOfPublication()).isEqualTo(year);
        Assertions.assertThat(actual.get(1).getYearOfPublication()).isEqualTo(year);
    }

    @Test
    void findByRate() {
        //given:
        int rate =2;
        UUID id = UUID.fromString("b8715a07-959a-4326-afff-0cfc74a6e85b");

        Book book = new Book();
        book.setId(id);
        book.setTitle("Obcy");
        book.setAuthor("Albert Camus");
        book.setYearOfPublication(1942);
        book.setCountry("Francja");
        book.setRate(2);

        testBookRepository.save(book);
        //when:
        List<Book> actual = testBookRepository.findByRate(rate);

        //then:
        Assertions.assertThat(actual.get(0).getRate()).isEqualTo(rate);
    }

    @Test
    void findById() {
        //given:
        UUID id = UUID.fromString("b8715a07-959a-4326-afff-0cfc74a6e85b");

        Book book = new Book();
        book.setId(id);
        book.setTitle("Obcy");
        book.setAuthor("Albert Camus");
        book.setYearOfPublication(1942);
        book.setCountry("Francja");
        book.setRate(4);

        testBookRepository.save(book);
        //when:
        Optional<Book> actual = testBookRepository.findById(id);

        //then:
        Assertions.assertThat(actual.get().getId()).isEqualTo(id);
    }
}