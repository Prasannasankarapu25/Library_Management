package com.library.management.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.management.entity.BorrowRecord;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, UUID> {

	@Query("SELECT br FROM BorrowRecord br WHERE br.returnDate IS NULL")
	List<BorrowRecord> findActiveRecords();

	@Query("SELECT DISTINCT br FROM BorrowRecord br WHERE br.borrower.id = :borrowerId")
	List<BorrowRecord> findByBorrowerId(@Param("borrowerId") UUID borrowerId);

	@Query("SELECT br FROM BorrowRecord br WHERE br.dueDate < :today AND br.returnDate IS NULL")
	List<BorrowRecord> findOverdue(LocalDate today);

	@Query("SELECT br FROM BorrowRecord br WHERE br.book.id = :bookId AND br.returnDate IS NULL")
	List<BorrowRecord> findActiveByBookId(UUID bookId);
}
