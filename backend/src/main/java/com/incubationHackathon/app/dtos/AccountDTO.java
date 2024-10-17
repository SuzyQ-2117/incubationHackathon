package com.incubationHackathon.app.dtos;

import com.incubationHackathon.app.entities.Account;

public class AccountDTO {

    private Long accountId;
    private String sortCode;
    private String accountNumber;
    private String productCode;
    private String accountType;
    private double balance;

    // Constructors, Getters, and Setters
    public AccountDTO() {}

    public AccountDTO(Long accountId, String sortCode, String accountNumber, String productCode, String accountType, double balance) {
        this.accountId = accountId;
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
        this.productCode = productCode;
        this.accountType = accountType;
        this.balance = balance;
    }

    public AccountDTO(String sortCode, String accountNumber, String productCode, String string, double balance) {
    }

    public AccountDTO(long l, String account1, Account.AccountType accountType) {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
