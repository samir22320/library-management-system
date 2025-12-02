package com.samir.librarymanagement.controller;

import com.samir.librarymanagement.dto.Borrow.BorrowRequest;
import com.samir.librarymanagement.dto.Borrow.BorrowResponse;
import com.samir.librarymanagement.service.Borrow.BorrowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    public final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public ResponseEntity<BorrowResponse> createBorrowRecord(@Valid @RequestBody BorrowRequest borrowRequest)
    {
        BorrowResponse borrowResponse = borrowService.createBorrowRecord(borrowRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BorrowResponse> returnBook(@PathVariable("id") Long recordId)
    {
        BorrowResponse borrowResponse = borrowService.returnBook(recordId);
        return ResponseEntity.ok(borrowResponse);
    }
    @GetMapping
    public ResponseEntity<List<BorrowResponse>> getAllRecord()
    {
        List<BorrowResponse> borrowResponses = borrowService.getAllRecord();
        return ResponseEntity.ok(borrowResponses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BorrowResponse> getRecordById(@PathVariable("id" )Long recordId)
    {
        BorrowResponse borrowResponse = borrowService.getRecordById(recordId);
        return ResponseEntity.ok(borrowResponse);
    }



}
