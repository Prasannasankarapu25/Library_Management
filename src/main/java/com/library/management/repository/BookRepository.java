package com.library.management.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.library.management.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {

	Optional<Book> findByTitleIgnoreCase(String title);

	@Query("SELECT b FROM Book b WHERE b.softDeleted = false")
	Page<Book> findAllActive(Pageable pageable);

	@Query("SELECT b FROM Book b WHERE b.id = :id AND b.softDeleted = false")
	Optional<Book> findActiveById(UUID id);
}
