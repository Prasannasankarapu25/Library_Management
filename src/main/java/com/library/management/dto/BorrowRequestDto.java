package com.library.management.dto;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;

public class BorrowRequestDto {

	@NotNull
	private UUID bookId;

	@NotNull
	private UUID borrowerId;

	public BorrowRequestDto() {
	}

	public BorrowRequestDto(UUID bookId, UUID borrowerId) {
		this.bookId = bookId;
		this.borrowerId = borrowerId;
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

	public static class Builder {
		private UUID bookId;
		private UUID borrowerId;

		public Builder bookId(UUID bookId) {
			this.bookId = bookId;
			return this;
		}

		public Builder borrowerId(UUID borrowerId) {
			this.borrowerId = borrowerId;
			return this;
		}

		public BorrowRequestDto build() {
			return new BorrowRequestDto(bookId, borrowerId);
		}
	}

	public static Builder builder() {
		return new Builder();
	}
}
