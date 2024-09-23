package com.example.bookslibrary.dto;

import java.util.UUID;

public record BookDto(
        UUID id,
        String title,
        String author,
        Integer year,
        String country,
        Integer rate
){}
