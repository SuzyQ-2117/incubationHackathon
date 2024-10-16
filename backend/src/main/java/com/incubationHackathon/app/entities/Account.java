package com.incubationHackathon.app.entities;

import jakarta.persistence.*;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    public enum AccountType {
        CURRENT,
        SAVINGS,
        CREDIT_CARD
    }

    // Constructors, Getters, and Setters

    public Account() {}

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String s) {
        this.productCode = this.sortCode + this.accountNumber;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
