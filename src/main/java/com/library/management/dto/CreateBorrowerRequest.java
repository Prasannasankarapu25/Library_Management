package com.library.management.dto;

import com.library.management.entity.Borrower;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateBorrowerRequest {

	@NotBlank
	private String name;

	@Email
	private String email;

	private Borrower.MembershipType membershipType;

	public CreateBorrowerRequest() {
	}

	public CreateBorrowerRequest(String name, String email, Borrower.MembershipType membershipType) {
		this.name = name;
		this.email = email;
		this.membershipType = membershipType;
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

	public static class Builder {
		private String name;
		private String email;
		private Borrower.MembershipType membershipType;

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

		public CreateBorrowerRequest build() {
			return new CreateBorrowerRequest(name, email, membershipType);
		}
	}

	public static Builder builder() {
		return new Builder();
	}
}
