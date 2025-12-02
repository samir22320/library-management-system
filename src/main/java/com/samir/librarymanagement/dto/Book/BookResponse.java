package com.samir.librarymanagement.dto.Book;

import lombok.*;

@Data
@AllArgsConstructor
public class BookResponse {
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private int availableCopies;

}
