package com.example.bookslibrary.book;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Entity
@Getter
@Setter
@Table(name="books")
@NoArgsConstructor

public class Book {
    @Id
    private UUID id = UUID.randomUUID();
    private String title;
    private String author;
    private Integer yearOfPublication;
    private String country;
    private Integer rate;

    public Book(Integer rate) {
        this.rate = rate;
    }

    public Book(String title, String author, Integer yearOfPublication, String country, Integer rate) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.country = country;
        this.rate = rate;
    }
}
