package com.samir.librarymanagement.service.Book;

import com.samir.librarymanagement.dto.Book.BookRequest;
import com.samir.librarymanagement.dto.Book.BookResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface BookService {
    BookResponse createBook(@Valid BookRequest bookRequest);

    List<BookResponse> getAllBook();

    BookResponse getBookById(Long bookId);

    BookResponse UpdateBookById(Long bookId, BookRequest bookRequest);

    void deleteBookById(Long bookId);

    BookResponse getBookByIsbn(String isbn);
}
