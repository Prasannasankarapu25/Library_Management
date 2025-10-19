package com.library.management.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.management.dto.BorrowResponseDto;
import com.library.management.dto.BorrowerDto;
import com.library.management.dto.CreateBorrowerRequest;
import com.library.management.entity.BorrowRecord;
import com.library.management.entity.Borrower;
import com.library.management.exception.NotFoundException;
import com.library.management.repository.BorrowRecordRepository;
import com.library.management.repository.BorrowerRepository;
import com.library.management.service.BorrowerService;

@Service
@Transactional
public class BorrowerServiceImpl implements BorrowerService {

	@Autowired
	private BorrowerRepository borrowerRepository;

	@Autowired
	private BorrowRecordRepository borrowRecordRepository;

	public BorrowerServiceImpl(BorrowerRepository borrowerRepository, BorrowRecordRepository borrowRecordRepository) {
		this.borrowerRepository = borrowerRepository;
		this.borrowRecordRepository = borrowRecordRepository;
	}

	private BorrowerDto toDto(Borrower b) {
		return BorrowerDto.builder().id(b.getId()).name(b.getName()).email(b.getEmail())
				.membershipType(b.getMembershipType()).maxBorrowLimit(b.getMaxBorrowLimit()).build();
	}

	@Override
	public BorrowerDto register(CreateBorrowerRequest req) {
		if (borrowerRepository.findByEmail(req.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email already registered");
		}
		Borrower b = Borrower.builder().name(req.getName()).email(req.getEmail())
				.membershipType(
						req.getMembershipType() == null ? Borrower.MembershipType.BASIC : req.getMembershipType())
				.build();
		b.applyMembershipDefaults();
		return toDto(borrowerRepository.save(b));
	}

	@Override
	public BorrowerDto getById(UUID id) {
		Borrower b = borrowerRepository.findById(id).orElseThrow(() -> new NotFoundException("Borrower not found"));
		return toDto(b);
	}

	@Override
	public List<BorrowResponseDto> getBorrowHistory(UUID borrowerId) {
		List<BorrowRecord> list = borrowRecordRepository.findByBorrowerId(borrowerId);
		return list.stream().map(this::toBorrowResponse).collect(Collectors.toList());
	}

	private BorrowResponseDto toBorrowResponse(BorrowRecord r) {
		return BorrowResponseDto.builder().id(r.getId()).bookId(r.getBook().getId()).borrowerId(r.getBorrower().getId())
				.borrowDate(r.getBorrowDate()).dueDate(r.getDueDate()).returnDate(r.getReturnDate())
				.fineAmount(r.getFineAmount()).build();
	}

	@Override
	public List<BorrowerDto> getOverdueBorrowers() {
		var overdue = borrowRecordRepository.findOverdue(java.time.LocalDate.now());
		return overdue.stream().map(BorrowRecord::getBorrower).distinct().map(this::toDto).collect(Collectors.toList());
	}
}
