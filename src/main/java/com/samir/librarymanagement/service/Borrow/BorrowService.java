package com.samir.librarymanagement.service.Borrow;

import com.samir.librarymanagement.dto.Borrow.BorrowRequest;
import com.samir.librarymanagement.dto.Borrow.BorrowResponse;

import java.util.List;

public interface BorrowService {
    BorrowResponse createBorrowRecord(BorrowRequest borrowRequest);

    BorrowResponse returnBook(Long recordId);

    List<BorrowResponse> getAllRecord();

    BorrowResponse getRecordById(Long recordId);
}
