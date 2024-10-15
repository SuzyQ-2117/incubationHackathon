package com.incubationHackathon.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String type; // e.g., DD, DEB, FPI
    private int merchantCode;
    private ZonedDateTime timestamp;
    private double amount;

    public Transaction() {
    }

    public Transaction(Long transactionId, String type, int merchantCode, ZonedDateTime timestamp, double amount) {
        this.transactionId = transactionId;
        this.type = type;
        this.merchantCode = merchantCode;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(int merchantCode) {
        this.merchantCode = merchantCode;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "Transaction Id=" + transactionId +
                ", Type='" + type + '\'' +
                ", Merchant Code=" + merchantCode +
                ", Timestamp=" + timestamp +
                ", Amount=" + amount +
                '}';
    }
}
