package com.incubationHackathon.accounts.controller;

import com.incubationHackathon.accounts.dto.AccountDTO;
import com.incubationHackathon.accounts.entity.Account;
import com.incubationHackathon.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Retrieve all accounts
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // Retrieve a specific account by ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        AccountDTO account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    // Add a new account
    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody Account account) {
        AccountDTO createdAccount = accountService.addAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    // Delete an account by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    // Retrieve accounts by type
    @GetMapping("/type/{accountType}")
    public ResponseEntity<List<AccountDTO>> getAccountsByType(@PathVariable Account.AccountType accountType) {
        List<AccountDTO> accounts = accountService.getAccountsByType(accountType);
        return ResponseEntity.ok(accounts);
    }
}