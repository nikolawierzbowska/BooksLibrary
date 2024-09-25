package com.example.bookslibrary.book;

import com.example.bookslibrary.book.dto.BookDto;
import com.example.bookslibrary.book.dto.UpdatedBookDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private BookService bookService;


    @Test
    void getBooks() throws Exception {
        //given:
        UUID id1 = UUID.randomUUID();
        BookDto bookDto1 = new BookDto(id1,"Obcy", "Albert Camus", 1942, "Francja", 4);

        UUID id2 = UUID.randomUUID();
        BookDto bookDto2 = new BookDto(id2,"Wielki Gatsby", "Francisa Scotta Fitzgeralda", 1925,
                "USA", null);

        List<BookDto> booksDto = List.of(bookDto1, bookDto2);

        Mockito.when(bookService.getBooks())
                .thenReturn(booksDto);

        //when:
        ResultActions response = mockMvc.perform(get("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON));

        //then:
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Obcy"))
                .andExpect(jsonPath("$[1].title").value("Wielki Gatsby"));
    }

    @Test
    void getBooksSortedByTitleAscending() throws Exception {
        //given:
        String asc= "ASC";
        UUID id1 = UUID.randomUUID();
        BookDto bookDto1 = new BookDto(id1,"Obcy", "Albert Camus", 1942, "Francja", 4);

        UUID id2 = UUID.randomUUID();
        BookDto bookDto2 = new BookDto(id2,"Wielki Gatsby", "Albert Camus", 1925,"USA",
                null);

        List<BookDto> booksDto = List.of(bookDto1, bookDto2);

        Mockito.when(bookService.getBooksSortedAscendingByTitle())
                .thenReturn(booksDto);

        //when:
        ResultActions response = mockMvc.perform(get("/api/v1/books")
                .param("titleSort", asc.toUpperCase())
                .contentType(MediaType.APPLICATION_JSON));

        //then:
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").value(booksDto.get(0).id().toString()))
                .andExpect(jsonPath("$[1].id").value(booksDto.get(1).id().toString()))
                .andExpect(jsonPath("$[0].title").value("Obcy"))
                .andExpect(jsonPath("$[1].title").value("Wielki Gatsby"));
    }

    @Test
    void getBooksSortedByTitleDescending() throws Exception {
        //given:
        String dsc= "DSC";
        UUID id1 = UUID.randomUUID();
        BookDto bookDto1 = new BookDto(id1,"Obcy", "Albert Camus", 1942, "Francja", 4);

        UUID id2 = UUID.randomUUID();
        BookDto bookDto2 = new BookDto(id2,"Wielki Gatsby", "Albert Camus", 1925,"USA",
                null);

        List<BookDto> booksDto = List.of(bookDto2, bookDto1);

        Mockito.when(bookService.getBooksSortedDescendingByTitle())
                .thenReturn(booksDto);

        //when:
        ResultActions response = mockMvc.perform(get("/api/v1/books")
                .param("titleSort", dsc.toUpperCase())
                .contentType(MediaType.APPLICATION_JSON));

        //then:
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").value(booksDto.get(0).id().toString()))
                .andExpect(jsonPath("$[1].id").value(booksDto.get(1).id().toString()))
                .andExpect(jsonPath("$[0].title").value("Wielki Gatsby"))
                .andExpect(jsonPath("$[1].title").value("Obcy"));
    }



    @Test
    void getBookFilterByTitle() throws Exception {
        //given:
        String title = "Obcy";
        UUID id1 = UUID.randomUUID();
        BookDto bookDto = new BookDto(id1,"Obcy", "Albert Camus", 1942, "Francja", 4);

        Mockito.when(bookService.getBookFilterByTitle(title))
                .thenReturn(bookDto);

        //when:
        ResultActions response = mockMvc.perform(get("/api/v1/books")
                .param("title", title)
                .contentType(MediaType.APPLICATION_JSON));

        //then:
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(bookDto.id().toString()))
                .andExpect(jsonPath("$.country").value("Francja"))
                .andExpect(jsonPath("$.year").value(1942));
    }

    @Test
    void getBooksFilterByAuthor() throws Exception {
        //given:
        String author = "Albert Camus";
        UUID id1 = UUID.randomUUID();
        BookDto bookDto1 = new BookDto(id1,"Obcy", "Albert Camus", 1942, "Francja", 4);

        UUID id2 = UUID.randomUUID();
        BookDto bookDto2 = new BookDto(id2,"Wielki Gatsby", "Albert Camus", 1925,
                "USA", null);

        List<BookDto> booksDto = List.of(bookDto1, bookDto2);

        Mockito.when(bookService.getBooksFilterByAuthor(author))
                .thenReturn(booksDto);

        //when:
        ResultActions response = mockMvc.perform(get("/api/v1/books")
                        .param("author", author)
                .contentType(MediaType.APPLICATION_JSON));

        //then:
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(booksDto.get(0).id().toString()))
                .andExpect(jsonPath("$[1].id").value(booksDto.get(1).id().toString()))
                .andExpect(jsonPath("$[1].title").value("Wielki Gatsby"));
    }

    @Test
    void getBooksFilterByYear() throws Exception {
        //given:
        int year =1925;

        UUID id2 = UUID.randomUUID();
        BookDto bookDto2 = new BookDto(id2,"Wielki Gatsby", "Albert Camus", 1925,
                "USA", null);

        List<BookDto> booksDto = List.of( bookDto2);

        Mockito.when(bookService.getBooksFilterByYear(year))
                .thenReturn(booksDto);

        //when:
        ResultActions response = mockMvc.perform(get("/api/v1/books")
                .param("year", String.valueOf(year))
                .contentType(MediaType.APPLICATION_JSON));

        //then:
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(booksDto.get(0).id().toString()))
                .andExpect(jsonPath("$[0].title").value("Wielki Gatsby"))
                .andExpect(jsonPath("$[0].country").value("USA"));
    }

    @Test
    void getBooksFilterByRate() throws Exception {
        //given:
        int rate =2;

        UUID id2 = UUID.randomUUID();
        BookDto bookDto2 = new BookDto(id2,"Wielki", "Albert Camus", 1935,
                "Polska", 2);

        List<BookDto> booksDto = List.of( bookDto2);

        Mockito.when(bookService.getBooksFilterByRate(rate))
                .thenReturn(booksDto);

        //when:
        ResultActions response = mockMvc.perform(get("/api/v1/books")
                .param("rate", String.valueOf(rate))
                .contentType(MediaType.APPLICATION_JSON));

        //then:
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(booksDto.get(0).id().toString()))
                .andExpect(jsonPath("$[0].title").value("Wielki"))
                .andExpect(jsonPath("$[0].rate").value(rate))
                .andExpect(jsonPath("$[0].country").value("Polska"));
    }

    @Test
    void updateRate() throws Exception {
        //given:
        UUID id = UUID.randomUUID();
        Book book = new Book("Wielki", "Albert Camus", 1935,"Polska", 2);
        UpdatedBookDto updatedBookDto = new UpdatedBookDto(5);

        Mockito.when(bookRepository.findById(id))
                .thenReturn(Optional.of(book));

        bookService.updateRateOfBook(id, updatedBookDto);

        //when:
        ResultActions response = mockMvc.perform(patch("/api/v1/books/"+ id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                            "rate": 5
                            }
                        """));

        //then:
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}