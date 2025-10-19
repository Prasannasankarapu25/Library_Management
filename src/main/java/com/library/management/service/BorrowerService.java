package com.library.management.service;

import java.util.List;
import java.util.UUID;

import com.library.management.dto.BorrowResponseDto;
import com.library.management.dto.BorrowerDto;
import com.library.management.dto.CreateBorrowerRequest;

public interface BorrowerService {
	BorrowerDto register(CreateBorrowerRequest req);

	BorrowerDto getById(UUID id);

	List<BorrowResponseDto> getBorrowHistory(UUID borrowerId);

	List<BorrowerDto> getOverdueBorrowers();
}
