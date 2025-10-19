package com.library.management.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.management.dto.BookDto;
import com.library.management.dto.CreateBookRequest;
import com.library.management.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	public ResponseEntity<BookDto> addBook(@Valid @RequestBody CreateBookRequest req) {
		return ResponseEntity.ok(bookService.addOrUpdateBook(req));
	}

	@GetMapping
	public ResponseEntity<Page<BookDto>> getBooks(@RequestParam(required = false) String category,
			@RequestParam(required = false) String author, @RequestParam(required = false) Boolean available,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<BookDto> books = bookService.listBooks(category, author, available, pageable);
		return ResponseEntity.ok(books);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookDto> getBook(@PathVariable UUID id) {
		return ResponseEntity.ok(bookService.getBook(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookDto> updateBook(@PathVariable UUID id, @Valid @RequestBody CreateBookRequest req) {
		return ResponseEntity.ok(bookService.updateBook(id, req));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> softDelete(@PathVariable UUID id) {
		bookService.softDelete(id);
		return ResponseEntity.ok("Book soft-deleted");
	}
}
