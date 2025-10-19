package com.library.management.dto;

import java.util.UUID;

public class BookDto {

	private UUID id;
	private String title;
	private String author;
	private String category;
	private boolean isAvailable;
	private int totalCopies;
	private int availableCopies;

	private BookDto(Builder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.author = builder.author;
		this.category = builder.category;
		this.isAvailable = builder.isAvailable;
		this.totalCopies = builder.totalCopies;
		this.availableCopies = builder.availableCopies;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	public static class Builder {
		private UUID id;
		private String title;
		private String author;
		private String category;
		private boolean isAvailable;
		private int totalCopies;
		private int availableCopies;

		public Builder id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder author(String author) {
			this.author = author;
			return this;
		}

		public Builder category(String category) {
			this.category = category;
			return this;
		}

		public Builder isAvailable(boolean isAvailable) {
			this.isAvailable = isAvailable;
			return this;
		}

		public Builder totalCopies(int totalCopies) {
			this.totalCopies = totalCopies;
			return this;
		}

		public Builder availableCopies(int availableCopies) {
			this.availableCopies = availableCopies;
			return this;
		}

		public BookDto build() {
			return new BookDto(this);
		}
	}

	public static Builder builder() {
		return new Builder();
	}
}
