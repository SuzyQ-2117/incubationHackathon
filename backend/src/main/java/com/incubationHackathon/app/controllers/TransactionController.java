package com.incubationHackathon.app.controllers;

import com.incubationHackathon.app.dtos.TransactionDTO;
import com.incubationHackathon.app.entities.Transaction;
import com.incubationHackathon.app.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint to retrieve all transactions from the last 28 days
    @GetMapping("/recent")
    public ResponseEntity<List<TransactionDTO>> getRecentTransactions() {
        List<TransactionDTO> transactions = transactionService.getTransactionsWithinLast28Days();
        return ResponseEntity.ok(transactions);
    }

    // Endpoint to add a new transaction
    @PostMapping
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody Transaction transaction) {
        TransactionDTO createdTransaction = transactionService.addTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    // Endpoint to update the category of a transaction
    @PutMapping("/{id}/category")
    public ResponseEntity<TransactionDTO> updateTransactionCategory(@PathVariable Long id, @RequestParam String category) {
        TransactionDTO updatedTransaction = transactionService.updateTransactionCategory(id, category);
        return ResponseEntity.ok(updatedTransaction);
    }
}
