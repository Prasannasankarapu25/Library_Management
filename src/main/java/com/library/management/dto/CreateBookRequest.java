package com.library.management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateBookRequest {

	@NotBlank
	private String title;

	@NotBlank
	private String author;

	@NotBlank
	private String category;

	@Min(1)
	private int totalCopies;

	public CreateBookRequest() {
	}

	public CreateBookRequest(String title, String author, String category, int totalCopies) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.totalCopies = totalCopies;
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

	public int getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	public static class Builder {
		private String title;
		private String author;
		private String category;
		private int totalCopies;

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

		public Builder totalCopies(int totalCopies) {
			this.totalCopies = totalCopies;
			return this;
		}

		public CreateBookRequest build() {
			return new CreateBookRequest(title, author, category, totalCopies);
		}
	}

	public static Builder builder() {
		return new Builder();
	}
}
