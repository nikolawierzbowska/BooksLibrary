package com.example.bookslibrary;

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
    private Integer year;
    private String country;
    private Integer rate;

    public Book(Integer rate) {
        this.rate = rate;
    }
}
