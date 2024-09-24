package com.example.bookslibrary.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatedBookDto {

    private int rate;

    public UpdatedBookDto(int rate) {
        this.rate = rate;
    }
}
