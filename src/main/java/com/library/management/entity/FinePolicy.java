package com.library.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fine_policy")
public class FinePolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String category;

	private double finePerDay;

	public FinePolicy() {

	}

	public FinePolicy(Long id, String category, double finePerDay) {
		this.id = id;
		this.category = category;
		this.finePerDay = finePerDay;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getFinePerDay() {
		return finePerDay;
	}

	public void setFinePerDay(double finePerDay) {
		this.finePerDay = finePerDay;
	}

	public static FinePolicyBuilder builder() {
		return new FinePolicyBuilder();
	}

	public static class FinePolicyBuilder {
		private Long id;
		private String category;
		private double finePerDay;

		public FinePolicyBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public FinePolicyBuilder category(String category) {
			this.category = category;
			return this;
		}

		public FinePolicyBuilder finePerDay(double finePerDay) {
			this.finePerDay = finePerDay;
			return this;
		}

		public FinePolicy build() {
			return new FinePolicy(id, category, finePerDay);
		}
	}

}
