package com.library.management.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.management.dto.BookDto;
import com.library.management.dto.CreateBookRequest;
import com.library.management.entity.Book;
import com.library.management.exception.BadRequestException;
import com.library.management.exception.NotFoundException;
import com.library.management.repository.BookRepository;
import com.library.management.service.BookService;
import com.library.management.specification.BookSpecification;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	private BookDto toDto(Book b) {
		if (b == null)
			return null;
		return BookDto.builder().id(b.getId()).title(b.getTitle()).author(b.getAuthor()).category(b.getCategory())
				.isAvailable(b.isAvailable()).totalCopies(b.getTotalCopies()).availableCopies(b.getAvailableCopies())
				.build();
	}

	@Override
	public BookDto addOrUpdateBook(CreateBookRequest req) {
		var existing = bookRepository.findByTitleIgnoreCase(req.getTitle());
		if (existing.isPresent()) {
			Book book = existing.get();

			book.setTotalCopies(book.getTotalCopies() + req.getTotalCopies());
			book.setAvailableCopies(book.getAvailableCopies() + req.getTotalCopies());
			book.setAvailable(book.getAvailableCopies() > 0);
			return toDto(bookRepository.save(book));
		}

		Book book = Book.builder().title(req.getTitle()).author(req.getAuthor()).category(req.getCategory())
				.totalCopies(req.getTotalCopies()).availableCopies(req.getTotalCopies())
				.isAvailable(req.getTotalCopies() > 0).softDeleted(false).build();

		return toDto(bookRepository.save(book));
	}

	@Override
	public BookDto getBook(UUID id) {
		Book book = bookRepository.findActiveById(id).orElseThrow(() -> new NotFoundException("Book not found"));
		return toDto(book);
	}

	@Override
	public BookDto updateBook(UUID id, CreateBookRequest req) {
		Book book = bookRepository.findActiveById(id).orElseThrow(() -> new NotFoundException("Book not found"));

		int diff = req.getTotalCopies() - book.getTotalCopies();
		book.setTitle(req.getTitle());
		book.setAuthor(req.getAuthor());
		book.setCategory(req.getCategory());
		book.setTotalCopies(req.getTotalCopies());
		book.setAvailableCopies(Math.max(0, book.getAvailableCopies() + diff));
		book.setAvailable(book.getAvailableCopies() > 0);

		return toDto(bookRepository.save(book));
	}

	@Override
	public void softDelete(UUID id) {
		Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));

		boolean hasActive = book.getBorrowRecords().stream().anyMatch(br -> br.getReturnDate() == null);

		if (hasActive) {
			throw new BadRequestException("Cannot delete book: there are active borrow records");
		}

		book.setSoftDeleted(true);
		bookRepository.save(book);
	}

	@Override
	public Page<BookDto> listBooks(String category, String author, Boolean available, Pageable pageable) {
		Specification<Book> spec = Specification.where(BookSpecification.isNotSoftDeleted())
				.and(BookSpecification.hasCategory(category)).and(BookSpecification.hasAuthor(author))
				.and(BookSpecification.isAvailable(available));

		Page<Book> page = bookRepository.findAll(spec, pageable);
		return page.map(this::toDto);
	}

}
