package com.library.management.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "borrow_records")
public class BorrowRecord {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "borrower_id", nullable = false)
	private Borrower borrower;

	private LocalDate borrowDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private double fineAmount;

	public BorrowRecord() {
	}

	public BorrowRecord(UUID id, Book book, Borrower borrower, LocalDate borrowDate, LocalDate dueDate,
			LocalDate returnDate, double fineAmount) {
		this.id = id;
		this.book = book;
		this.borrower = borrower;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.fineAmount = fineAmount;
	}

	// ======= Getters and Setters =======
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public double getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}

	public static class Builder {
		private UUID id;
		private Book book;
		private Borrower borrower;
		private LocalDate borrowDate;
		private LocalDate dueDate;
		private LocalDate returnDate;
		private double fineAmount;

		public Builder id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder book(Book book) {
			this.book = book;
			return this;
		}

		public Builder borrower(Borrower borrower) {
			this.borrower = borrower;
			return this;
		}

		public Builder borrowDate(LocalDate borrowDate) {
			this.borrowDate = borrowDate;
			return this;
		}

		public Builder dueDate(LocalDate dueDate) {
			this.dueDate = dueDate;
			return this;
		}

		public Builder returnDate(LocalDate returnDate) {
			this.returnDate = returnDate;
			return this;
		}

		public Builder fineAmount(double fineAmount) {
			this.fineAmount = fineAmount;
			return this;
		}

		public BorrowRecord build() {
			return new BorrowRecord(id, book, borrower, borrowDate, dueDate, returnDate, fineAmount);
		}
	}

	public static Builder builder() {
		return new Builder();
	}
}
