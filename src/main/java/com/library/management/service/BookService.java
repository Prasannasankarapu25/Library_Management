package com.library.management.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.library.management.dto.BookDto;
import com.library.management.dto.CreateBookRequest;

public interface BookService {
	BookDto addOrUpdateBook(CreateBookRequest req);

	Page<BookDto> listBooks(String category, String author, Boolean available, Pageable pageable);

	BookDto getBook(UUID id);

	BookDto updateBook(UUID id, CreateBookRequest req);

	void softDelete(UUID id);
}
