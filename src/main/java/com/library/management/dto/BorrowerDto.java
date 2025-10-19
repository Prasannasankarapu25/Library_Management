package com.library.management.dto;

import java.util.UUID;
import com.library.management.entity.Borrower;

public class BorrowerDto {

	private UUID id;
	private String name;
	private String email;
	private Borrower.MembershipType membershipType;
	private int maxBorrowLimit;

	public BorrowerDto() {
	}

	public BorrowerDto(UUID id, String name, String email, Borrower.MembershipType membershipType, int maxBorrowLimit) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.membershipType = membershipType;
		this.maxBorrowLimit = maxBorrowLimit;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Borrower.MembershipType getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(Borrower.MembershipType membershipType) {
		this.membershipType = membershipType;
	}

	public int getMaxBorrowLimit() {
		return maxBorrowLimit;
	}

	public void setMaxBorrowLimit(int maxBorrowLimit) {
		this.maxBorrowLimit = maxBorrowLimit;
	}

	public static class Builder {
		private UUID id;
		private String name;
		private String email;
		private Borrower.MembershipType membershipType;
		private int maxBorrowLimit;

		public Builder id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder membershipType(Borrower.MembershipType membershipType) {
			this.membershipType = membershipType;
			return this;
		}

		public Builder maxBorrowLimit(int maxBorrowLimit) {
			this.maxBorrowLimit = maxBorrowLimit;
			return this;
		}

		public BorrowerDto build() {
			return new BorrowerDto(id, name, email, membershipType, maxBorrowLimit);
		}
	}

	public static Builder builder() {
		return new Builder();
	}
}
