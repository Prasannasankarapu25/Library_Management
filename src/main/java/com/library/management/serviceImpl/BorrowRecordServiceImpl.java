package com.library.management.serviceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.management.dto.BorrowResponseDto;
import com.library.management.entity.Book;
import com.library.management.entity.BorrowRecord;
import com.library.management.entity.Borrower;
import com.library.management.exception.BadRequestException;
import com.library.management.exception.NotFoundException;
import com.library.management.repository.BookRepository;
import com.library.management.repository.BorrowRecordRepository;
import com.library.management.repository.BorrowerRepository;
import com.library.management.service.BorrowRecordService;
import com.library.management.service.FinePolicyService;

@Service
@Transactional
public class BorrowRecordServiceImpl implements BorrowRecordService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BorrowerRepository borrowerRepository;
	@Autowired
	private BorrowRecordRepository borrowRecordRepository;

	@Autowired
	private FinePolicyService finePolicyService;

	public BorrowRecordServiceImpl(BookRepository bookRepository, BorrowerRepository borrowerRepository,
			BorrowRecordRepository borrowRecordRepository, FinePolicyService finePolicyService) {
		this.bookRepository = bookRepository;
		this.borrowerRepository = borrowerRepository;
		this.borrowRecordRepository = borrowRecordRepository;
		this.finePolicyService = finePolicyService;
	}

	@Override
	public BorrowResponseDto borrowBook(UUID bookId, UUID borrowerId) {
		Book book = bookRepository.findActiveById(bookId).orElseThrow(() -> new NotFoundException("Book not found"));
		Borrower borrower = borrowerRepository.findById(borrowerId)
				.orElseThrow(() -> new NotFoundException("Borrower not found"));

		long activeCount = borrowRecordRepository.findByBorrowerId(borrowerId).stream()
				.filter(r -> r.getReturnDate() == null).count();
		if (activeCount >= borrower.getMaxBorrowLimit()) {
			throw new BadRequestException("Borrow limit exceeded");
		}

		if (!book.isAvailable() || book.getAvailableCopies() <= 0) {
			throw new BadRequestException("Book not available");
		}

		LocalDate borrowDate = LocalDate.now();
		LocalDate dueDate = borrowDate.plusDays(14);

		BorrowRecord record = BorrowRecord.builder().book(book).borrower(borrower).borrowDate(borrowDate)
				.dueDate(dueDate).fineAmount(0.0).build();

		book.setAvailableCopies(book.getAvailableCopies() - 1);
		book.setAvailable(book.getAvailableCopies() > 0);

		book.addBorrowRecord(record);

		bookRepository.save(book);
		BorrowRecord saved = borrowRecordRepository.save(record);

		return toDto(saved);
	}

	@Override
	public BorrowResponseDto returnBook(UUID borrowRecordId) {
		BorrowRecord record = borrowRecordRepository.findById(borrowRecordId)
				.orElseThrow(() -> new NotFoundException("Borrow record not found"));

		if (record.getReturnDate() != null) {
			throw new BadRequestException("Book already returned");
		}

		LocalDate returnDate = LocalDate.now();
		record.setReturnDate(returnDate);

		if (returnDate.isAfter(record.getDueDate())) {
			long daysLate = ChronoUnit.DAYS.between(record.getDueDate(), returnDate);
			double fine = daysLate * finePolicyService.getFineForCategory(record.getBook().getCategory());
			record.setFineAmount(fine);
		}

		Book book = record.getBook();
		book.setAvailableCopies(book.getAvailableCopies() + 1);
		book.setAvailable(true);

		bookRepository.save(book);
		BorrowRecord saved = borrowRecordRepository.save(record);

		return toDto(saved);
	}

	@Override
	public List<BorrowResponseDto> listActiveRecords() {
		return borrowRecordRepository.findActiveRecords().stream().map(this::toDto).collect(Collectors.toList());
	}

	private BorrowResponseDto toDto(BorrowRecord r) {
		return BorrowResponseDto.builder().id(r.getId()).bookId(r.getBook().getId()).borrowerId(r.getBorrower().getId())
				.borrowDate(r.getBorrowDate()).dueDate(r.getDueDate()).returnDate(r.getReturnDate())
				.fineAmount(r.getFineAmount()).build();
	}
}
