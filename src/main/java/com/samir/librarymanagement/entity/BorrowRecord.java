package com.samir.librarymanagement.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private BorrowStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;
}
