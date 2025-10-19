package com.library.management.service;

import java.util.List;

import com.library.management.entity.FinePolicy;

public interface FinePolicyService {
	FinePolicy createOrUpdate(String category, double finePerDay);

	List<FinePolicy> getAll();

	double getFineForCategory(String category);
}
