package com.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.entity.FinePolicy;
import com.library.management.service.FinePolicyService;

@RestController
@RequestMapping("/api/fines")

public class FinePolicyController {

	@Autowired
	private FinePolicyService finePolicyService;

	public FinePolicyController(FinePolicyService finePolicyService) {
		this.finePolicyService = finePolicyService;
	}

	@PostMapping
	public ResponseEntity<FinePolicy> createOrUpdate(@RequestParam String category, @RequestParam double finePerDay) {
		FinePolicy policy = finePolicyService.createOrUpdate(category, finePerDay);
		return ResponseEntity.ok(policy);
	}

	// Get all fine policies
	@GetMapping
	public ResponseEntity<List<FinePolicy>> getAll() {
		return ResponseEntity.ok(finePolicyService.getAll());
	}
}
