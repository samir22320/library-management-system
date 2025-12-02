package com.samir.librarymanagement.dto.Borrow;

import lombok.*;


import java.time.LocalDate;
@Data
@AllArgsConstructor
public class BorrowResponse {
    private Long id;
    private String memberName;
    private String bookTitle;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status;
}
