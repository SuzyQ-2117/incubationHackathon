package com.incubationHackathon.accounts.entity;

import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @Column(nullable = false, unique = true)
    private String productCode;

    private String sortCode;
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private double balance;

    @Column(nullable = false)
    private Long userId; // Reference to user without direct JPA mapping

    // Constructors, Getters, and Setters

    public enum AccountType {
        CURRENT,
        SAVINGS,
        CREDIT_CARD
    }

    public Account() {}

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
