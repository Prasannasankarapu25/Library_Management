package com.library.management.service;

import java.util.List;
import java.util.UUID;

import com.library.management.dto.BorrowResponseDto;

public interface BorrowRecordService {
	BorrowResponseDto borrowBook(UUID bookId, UUID borrowerId);

	BorrowResponseDto returnBook(UUID borrowRecordId);

	List<BorrowResponseDto> listActiveRecords();
}
