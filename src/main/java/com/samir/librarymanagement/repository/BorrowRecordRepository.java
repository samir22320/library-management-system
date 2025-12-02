package com.samir.librarymanagement.repository;

import com.samir.librarymanagement.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long> {
}
