package com.incubationHackathon.transactions.controller;

import com.incubationHackathon.transactions.dto.TransactionDTO;
import com.incubationHackathon.transactions.entity.Transaction;
import com.incubationHackathon.transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/transactions")  // Update path to differentiate microservice context
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint to retrieve all transactions from the last 28 days
    @GetMapping("/recent")
    public ResponseEntity<List<TransactionDTO>> getRecentTransactions() {
        List<TransactionDTO> transactions = transactionService.getTransactionsWithinLast28Days();
        return ResponseEntity.ok(transactions);
    }

    // Endpoint to update the category of a transaction
    @PutMapping("/{id}/category")
    public ResponseEntity<TransactionDTO> updateTransactionCategory(@PathVariable Long id, @RequestParam String category) {
        TransactionDTO updatedTransaction = transactionService.updateTransactionCategory(id, category);
        return ResponseEntity.ok(updatedTransaction);
    }
}
