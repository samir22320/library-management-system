package com.samir.librarymanagement.controller;

import com.samir.librarymanagement.dto.Book.BookRequest;
import com.samir.librarymanagement.dto.Book.BookResponse;
import com.samir.librarymanagement.service.Book.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    public final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookRequest)
    {
        BookResponse bookResponse = bookService.createBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);

    }
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBook()
    {
        List<BookResponse> bookResponses = bookService.getAllBook();
        return ResponseEntity.ok(bookResponses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") Long bookId)
    {
        BookResponse bookResponse = bookService.getBookById(bookId);
        return ResponseEntity.ok(bookResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> UpdateBookById(@PathVariable("id") Long bookId,@RequestBody BookRequest bookRequest)
    {
        BookResponse bookResponse = bookService.UpdateBookById(bookId,bookRequest);
        return ResponseEntity.ok(bookResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("id") Long bookId)
    {
        bookService.deleteBookById(bookId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponse> getBookByIsbn(@PathVariable("isbn")String isbn)
    {
        BookResponse bookResponse = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(bookResponse);
    }



}
