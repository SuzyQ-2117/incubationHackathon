package com.incubationHackathon.accounts.dto;

public class AccountDTO {

    private String sortCode;
    private String accountNumber;
    private String productCode;
    private String accountType;
    private double balance;
    private Long userId; // User reference

    // Constructors, Getters, and Setters
    public AccountDTO() {}

    public AccountDTO(Long accountId, String sortCode, String accountNumber, String productCode, String accountType, double balance, Long userId) {

        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
        this.productCode = productCode;
        this.accountType = accountType;
        this.balance = balance;
        this.userId = userId;
    }

    public AccountDTO(String sortCode, String accountNumber, String productCode, String string, double balance, Long userId) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
