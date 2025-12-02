package com.samir.librarymanagement.dto.Borrow;

import jakarta.validation.constraints.NotNull;
import lombok.*;
@Data
public class BorrowRequest {
    @NotNull(message = "Member Id is required")
    private Long memberid;
    @NotNull(message = "Book Id is required")
    private Long bookid;
}
