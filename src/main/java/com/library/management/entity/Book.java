package com.library.management.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	private String title;
	private String author;
	private String category;

	private boolean isAvailable;
	private int totalCopies;
	private int availableCopies;

	private boolean softDeleted = false;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BorrowRecord> borrowRecords = new ArrayList<>();

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

	public boolean isSoftDeleted() {
		return softDeleted;
	}

	public void setSoftDeleted(boolean softDeleted) {
		this.softDeleted = softDeleted;
	}

	public List<BorrowRecord> getBorrowRecords() {
		return borrowRecords;
	}

	public void setBorrowRecords(List<BorrowRecord> borrowRecords) {
		this.borrowRecords = borrowRecords;
	}

	public void addBorrowRecord(BorrowRecord record) {
		if (borrowRecords == null)
			borrowRecords = new ArrayList<>();
		borrowRecords.add(record);
		record.setBook(this);
	}

	public void removeBorrowRecord(BorrowRecord record) {
		if (borrowRecords != null) {
			borrowRecords.remove(record);
			record.setBook(null);
		}
	}

	public Book() {
	}

	public Book(UUID id, String title, String author, String category, boolean isAvailable, int totalCopies,
			int availableCopies, boolean softDeleted, List<BorrowRecord> borrowRecords) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.isAvailable = isAvailable;
		this.totalCopies = totalCopies;
		this.availableCopies = availableCopies;
		this.softDeleted = softDeleted;
		this.borrowRecords = borrowRecords != null ? borrowRecords : new ArrayList<>();
	}

	public static class Builder {
		private UUID id;
		private String title;
		private String author;
		private String category;
		private boolean isAvailable;
		private int totalCopies;
		private int availableCopies;
		private boolean softDeleted = false;
		private List<BorrowRecord> borrowRecords = new ArrayList<>();

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

		public Builder softDeleted(boolean softDeleted) {
			this.softDeleted = softDeleted;
			return this;
		}

		public Builder borrowRecords(List<BorrowRecord> borrowRecords) {
			this.borrowRecords = borrowRecords != null ? borrowRecords : new ArrayList<>();
			return this;
		}

		public Book build() {
			return new Book(id, title, author, category, isAvailable, totalCopies, availableCopies, softDeleted,
					borrowRecords);
		}
	}

	public static Builder builder() {
		return new Builder();
	}
}
