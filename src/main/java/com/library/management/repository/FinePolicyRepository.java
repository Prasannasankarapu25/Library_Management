package com.library.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.management.entity.FinePolicy;

@Repository
public interface FinePolicyRepository extends JpaRepository<FinePolicy, Long> {
	Optional<FinePolicy> findByCategoryIgnoreCase(String category);
}
