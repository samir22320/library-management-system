package com.samir.librarymanagement.service.Borrow;

import com.samir.librarymanagement.dto.Book.BookResponse;
import com.samir.librarymanagement.dto.Borrow.BorrowRequest;
import com.samir.librarymanagement.dto.Borrow.BorrowResponse;
import com.samir.librarymanagement.entity.Book;
import com.samir.librarymanagement.entity.BorrowRecord;
import com.samir.librarymanagement.entity.BorrowStatus;
import com.samir.librarymanagement.entity.Member;
import com.samir.librarymanagement.execption.ResourceNotFoundException;
import com.samir.librarymanagement.repository.BookRepository;
import com.samir.librarymanagement.repository.BorrowRecordRepository;
import com.samir.librarymanagement.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowServiceimpl implements BorrowService {

    public final BorrowRecordRepository borrowRecordRepository;
    public final MemberRepository memberRepository;
    public final BookRepository bookRepository;
    public BorrowServiceimpl(BorrowRecordRepository borrowRecordRepository, MemberRepository memberRepository, BookRepository bookRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    private BorrowResponse mapToDto(BorrowRecord record) {
        return new BorrowResponse(
                record.getId(),
                record.getMember().getName(),
                record.getBook().getTitle(),
                record.getBorrowDate(),
                record.getReturnDate(),
                record.getStatus().name()
        );
    }
    @Transactional
    @Override
    public BorrowResponse createBorrowRecord(BorrowRequest borrowRequest) {
        Member member = memberRepository.findById(borrowRequest.getMemberid()).orElseThrow(
                ()-> new ResourceNotFoundException("Member Id not founded" + borrowRequest.getMemberid()));
        Book book = bookRepository.findById(borrowRequest.getBookid()).orElseThrow(
                () -> new ResourceNotFoundException("Book Id not founded" + borrowRequest.getBookid()));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No available copies for this book");
        }
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setMember(member);
        borrowRecord.setBook(book);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setStatus(BorrowStatus.BORROWED);
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        BorrowRecord savedRecord = borrowRecordRepository.save(borrowRecord);
        return mapToDto(savedRecord);
    }
    @Transactional
    @Override
    public BorrowResponse returnBook(Long recordId)
    {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(recordId).orElseThrow(
                () -> new ResourceNotFoundException("This Record Id Not Found"+recordId));
        if(borrowRecord.getStatus() == BorrowStatus.RETURNED)
            throw new RuntimeException("Book already Returned");

        borrowRecord.setStatus(BorrowStatus.RETURNED);
        borrowRecord.setReturnDate(LocalDate.now());
        Book book = bookRepository.findById(borrowRecord.getBook().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Book Id Not Found " + borrowRecord.getBook().getId())
        );
        book.setAvailableCopies(book.getAvailableCopies()+1);
        bookRepository.save(book);
        BorrowRecord saveRecord = borrowRecordRepository.save(borrowRecord);
        return mapToDto(saveRecord);
    }

    @Override
    public List<BorrowResponse> getAllRecord() {
        List<BorrowRecord> borrowRecords = borrowRecordRepository.findAll();
        return borrowRecords.stream().map(this::mapToDto).toList();
    }

    @Override
    public BorrowResponse getRecordById(Long recordId) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(recordId).orElseThrow(
                () -> new ResourceNotFoundException("This Record Id Not Found"+recordId));
        return mapToDto(borrowRecord);
    }


}
