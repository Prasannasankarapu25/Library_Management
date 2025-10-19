package com.library.management.specification;

import org.springframework.data.jpa.domain.Specification;

import com.library.management.entity.Book;

public class BookSpecification {

	public static Specification<Book> hasCategory(String category) {
		return (root, query, cb) -> category == null ? null
				: cb.equal(cb.lower(root.get("category")), category.toLowerCase());
	}

	public static Specification<Book> hasAuthor(String author) {
		return (root, query, cb) -> author == null ? null
				: cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%");
	}

	public static Specification<Book> isAvailable(Boolean available) {
		return (root, query, cb) -> available == null ? null : cb.equal(root.get("isAvailable"), available);
	}

	public static Specification<Book> isNotSoftDeleted() {
		return (root, query, cb) -> cb.isFalse(root.get("softDeleted"));
	}
}
