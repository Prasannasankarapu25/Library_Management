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

import com.library.management.dto.BorrowRequestDto;
import com.library.management.dto.BorrowResponseDto;
import com.library.management.service.BorrowRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/borrow")
public class BorrowRecordController {

	@Autowired
	private BorrowRecordService borrowRecordService;

	public BorrowRecordController(BorrowRecordService borrowRecordService) {
		this.borrowRecordService = borrowRecordService;
	}

	@PostMapping
	public ResponseEntity<BorrowResponseDto> borrow(@Valid @RequestBody BorrowRequestDto req) {
		return ResponseEntity.ok(borrowRecordService.borrowBook(req.getBookId(), req.getBorrowerId()));
	}

	@PostMapping("/return/{id}")
	public ResponseEntity<BorrowResponseDto> returnBook(@PathVariable UUID id) {
		return ResponseEntity.ok(borrowRecordService.returnBook(id));
	}

	@GetMapping("/records/active")
	public ResponseEntity<List<BorrowResponseDto>> activeRecords() {
		return ResponseEntity.ok(borrowRecordService.listActiveRecords());
	}
}
