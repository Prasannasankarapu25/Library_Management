package com.library.management.dto;

import java.time.LocalDate;
import java.util.UUID;

public class BorrowResponseDto {

	private UUID id;
	private UUID bookId;
	private UUID borrowerId;
	private LocalDate borrowDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private double fineAmount;

	public BorrowResponseDto() {
	}

	public BorrowResponseDto(UUID id, UUID bookId, UUID borrowerId, LocalDate borrowDate, LocalDate dueDate,
			LocalDate returnDate, double fineAmount) {
		this.id = id;
		this.bookId = bookId;
		this.borrowerId = borrowerId;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.fineAmount = fineAmount;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getBookId() {
		return bookId;
	}

	public void setBookId(UUID bookId) {
		this.bookId = bookId;
	}

	public UUID getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(UUID borrowerId) {
		this.borrowerId = borrowerId;
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
		private UUID bookId;
		private UUID borrowerId;
		private LocalDate borrowDate;
		private LocalDate dueDate;
		private LocalDate returnDate;
		private double fineAmount;

		public Builder id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder bookId(UUID bookId) {
			this.bookId = bookId;
			return this;
		}

		public Builder borrowerId(UUID borrowerId) {
			this.borrowerId = borrowerId;
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

		public BorrowResponseDto build() {
			return new BorrowResponseDto(id, bookId, borrowerId, borrowDate, dueDate, returnDate, fineAmount);
		}
	}

	public static Builder builder() {
		return new Builder();
	}
}
