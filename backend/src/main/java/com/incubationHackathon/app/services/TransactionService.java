package com.incubationHackathon.app.services;

import com.incubationHackathon.app.dtos.TransactionDTO;
import com.incubationHackathon.app.entities.Transaction;
import com.incubationHackathon.app.repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionDTO> getTransactionsWithinLast28Days() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(28);
        List<Transaction> transactions = transactionRepository.findByTimestampBetween(startDate, endDate);

        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TransactionDTO addTransaction(Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        return convertToDTO(savedTransaction);
    }

    public TransactionDTO updateTransactionCategory(Long id, String category) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setUserCategory(category);
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return convertToDTO(updatedTransaction);
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getTransactionId(),
                transaction.getTransactionType(),
                transaction.getTimestamp(),
                transaction.getMerchantCode(),
                transaction.getUserCategory(),
                transaction.getAmount()
        );
    }
}
