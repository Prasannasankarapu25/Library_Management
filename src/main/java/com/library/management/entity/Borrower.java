package com.library.management.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "borrowers")
public class Borrower {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

    private int maxBorrowLimit;

    
    public Borrower() {}

    public Borrower(UUID id, String name, String email, MembershipType membershipType, int maxBorrowLimit) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.membershipType = membershipType;
        this.maxBorrowLimit = maxBorrowLimit;
    }

    
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public MembershipType getMembershipType() { return membershipType; }
    public void setMembershipType(MembershipType membershipType) { this.membershipType = membershipType; }

    public int getMaxBorrowLimit() { return maxBorrowLimit; }
    public void setMaxBorrowLimit(int maxBorrowLimit) { this.maxBorrowLimit = maxBorrowLimit; }

    
    public static class Builder {
        private UUID id;
        private String name;
        private String email;
        private MembershipType membershipType;
        private int maxBorrowLimit;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder membershipType(MembershipType membershipType) { this.membershipType = membershipType; return this; }
        public Builder maxBorrowLimit(int maxBorrowLimit) { this.maxBorrowLimit = maxBorrowLimit; return this; }

        public Borrower build() {
            return new Borrower(id, name, email, membershipType, maxBorrowLimit);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    
    public enum MembershipType {
        BASIC, PREMIUM
    }

  
    public void applyMembershipDefaults() {
        if (membershipType == MembershipType.BASIC) {
            this.maxBorrowLimit = 2;
        } else if (membershipType == MembershipType.PREMIUM) {
            this.maxBorrowLimit = 5;
        } else {
            this.maxBorrowLimit = 2;
        }
    }
}
