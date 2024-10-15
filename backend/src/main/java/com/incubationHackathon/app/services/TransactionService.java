package com.incubationHackathon.app.services;

import com.incubationHackathon.app.entities.Transaction;
import com.incubationHackathon.app.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElse(null);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long transactionId, Transaction transactionDetails) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);

        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setType(transactionDetails.getType());
            transaction.setMerchantCode(transactionDetails.getMerchantCode());
            transaction.setTimestamp(transactionDetails.getTimestamp());
            transaction.setAmount(transactionDetails.getAmount());
            return transactionRepository.save(transaction);
        } else {
            // Handle the case where the transaction doesn't exist
            return null;
        }
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}

