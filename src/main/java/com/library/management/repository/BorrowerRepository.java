package com.library.management.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.management.entity.Borrower;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, UUID> {
	Optional<Borrower> findByEmail(String email);
}
