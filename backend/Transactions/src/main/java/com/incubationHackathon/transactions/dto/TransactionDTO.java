package com.incubationHackathon.transactions.dto;

import java.time.LocalDateTime;

public class TransactionDTO {

    private Long transactionId;
    private String transactionType;
    private LocalDateTime timestamp;
    private int merchantCode;
    private String userCategory;
    private float amount;
    private String productCode; // Reference to Account

    // Constructors, Getters, and Setters

    public TransactionDTO() {}

    public TransactionDTO(Long transactionId, String transactionType, LocalDateTime timestamp, int merchantCode, String userCategory, float amount, String productCode) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.timestamp = timestamp;
        this.merchantCode = merchantCode;
        this.userCategory = userCategory;
        this.amount = amount;
        this.productCode = productCode;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(int merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode (String productCode) {
        this.productCode = productCode;
    }
}
