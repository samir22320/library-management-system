package com.samir.librarymanagement.dto.Book;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class BookRequest {
    @NotBlank(message = "Tittle is required")
    private String title;
    @NotBlank(message = "Author name is require")
    private String author;
    @Column(unique = true)
    @NotBlank(message = "ISBN is required")
    private String isbn;
    @NotNull(message = "available copies are required")
    private Integer availableCopies;
}
