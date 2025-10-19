package com.library.management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.dto.BorrowResponseDto;
import com.library.management.dto.BorrowerDto;
import com.library.management.dto.CreateBorrowerRequest;
import com.library.management.service.BorrowerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {

	@Autowired
	private BorrowerService borrowerService;

	public BorrowerController(BorrowerService borrowerService) {
		this.borrowerService = borrowerService;
	}

	@PostMapping
	public ResponseEntity<BorrowerDto> register(@Valid @RequestBody CreateBorrowerRequest req) {
		return ResponseEntity.ok(borrowerService.register(req));
	}

	@GetMapping("/{id}/records")
	public ResponseEntity<List<BorrowResponseDto>> history(@PathVariable UUID id) {
		return ResponseEntity.ok(borrowerService.getBorrowHistory(id));
	}

	@GetMapping("/overdue")
	public ResponseEntity<List<BorrowerDto>> overdueBorrowers() {
		return ResponseEntity.ok(borrowerService.getOverdueBorrowers());
	}
}
