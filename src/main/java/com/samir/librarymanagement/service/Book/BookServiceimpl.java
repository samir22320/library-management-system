package com.samir.librarymanagement.service.Book;

import com.samir.librarymanagement.dto.Book.BookRequest;
import com.samir.librarymanagement.dto.Book.BookResponse;
import com.samir.librarymanagement.entity.Book;
import com.samir.librarymanagement.execption.ResourceNotFoundException;
import com.samir.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceimpl implements BookService{

    public final BookRepository bookRepository;

    public BookServiceimpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    private final BookResponse mapToDto(Book book)
    {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getAvailableCopies()
        );
    }
    public void transferData(Book book ,BookRequest bookRequest)
    {
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setAvailableCopies(bookRequest.getAvailableCopies());
    }
    @Override
    public BookResponse createBook(BookRequest bookRequest) {
        if(bookRepository.existsByIsbn(bookRequest.getIsbn()))
        {
            throw new RuntimeException("ISBN is already exists");
        }
        Book book = new Book();
        transferData(book,bookRequest);
        Book savedBook = bookRepository.save(book);
        return mapToDto(savedBook);
    }

    @Override
    public List<BookResponse> getAllBook() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(book -> new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getAvailableCopies()
        )).toList();
    }

    @Override
    public BookResponse getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                ()-> new ResourceNotFoundException("Book id Not available "+ bookId));
            return mapToDto(book);
    }

    @Override
    public BookResponse UpdateBookById(Long bookId, BookRequest bookRequest) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book id Not available "+ bookId));
        if(!book.getIsbn().equals(bookRequest.getIsbn()) && bookRepository.existsByIsbn(bookRequest.getIsbn()))
        {
            throw new RuntimeException("this ISBN is already used before");
        }
        transferData(book,bookRequest);
        Book savedBook = bookRepository.save(book);
        return mapToDto(savedBook);
    }

    @Override
    public void deleteBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book id Not available "+ bookId));
        bookRepository.delete(book);
    }

    @Override
    public BookResponse getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(
                () -> new ResourceNotFoundException("ISBN not found " + isbn));
        return mapToDto(book);
    }


}
