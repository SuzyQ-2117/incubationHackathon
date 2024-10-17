package com.incubationHackathon.accounts.controller;

import com.incubationHackathon.accounts.dto.AccountDTO;
import com.incubationHackathon.accounts.entity.Account;
import com.incubationHackathon.accounts.service.AccountService;
import com.incubationHackathon.accounts.service.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtUtil jwtUtil;


    private Long getUserIdFromToken(String token) {
        try {
            String userId = jwtUtil.extractUsername(token);
            System.out.println("Extracted user ID from token: " + userId);
            return Long.parseLong(userId);
        } catch (JwtException | NumberFormatException e) {
            System.out.println("Error parsing user ID from token: " + e.getMessage());
            return null;
        }
    }

    // Retrieve all accounts for the logged-in user
    @GetMapping("/user")
    public ResponseEntity<List<AccountDTO>> getAllAccountsForUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = getUserIdFromToken(token);

        if (userId == null) {
            return ResponseEntity.status(401).build();
        }

        List<AccountDTO> accounts = accountService.getAccountsByUserId(userId);
        return ResponseEntity.ok(accounts);
    }

    //check if productCode already exists for the verified user
    @PostMapping("/product")
    public ResponseEntity<Optional<Account>> getAccountByProductCode(
            @RequestBody Map<String, String> requestBody, HttpServletRequest request) {
        String productCode = requestBody.get("productCode");
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = getUserIdFromToken(token);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Account> accountDTO = accountService.getAccountByProductCode(productCode, userId);
        return ResponseEntity.ok(accountDTO);
    }


    // Add a new account for the logged-in user
    @PostMapping("/add")
    public ResponseEntity<AccountDTO> addAccount(@RequestBody Account account, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = getUserIdFromToken(token);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Set the productCode and userId on the account object
        account.setProductCode(account.getSortCode() + account.getAccountNumber());
        account.setUserId(userId);

        try {
            // Save and return the created account
            AccountDTO createdAccount = accountService.addAccount(account);
            return ResponseEntity.ok(createdAccount);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }



    // Delete an account for the logged-in user by productCode
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAccountByProductCode(
            @RequestBody Map<String, String> requestBody, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = getUserIdFromToken(token);
        String productCode = requestBody.get("productCode");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Delete the account
        accountService.deleteAccountByProductCode(productCode, userId);
        return ResponseEntity.noContent().build();
    }



    // Remaining methods without JWT verification

    // Retrieve all accounts
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

//    // Retrieve a specific account by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
//        AccountDTO account = accountService.getAccountById(id);
//        return ResponseEntity.ok(account);
//    }

    // Retrieve accounts by type
    @GetMapping("/type/{accountType}")
    public ResponseEntity<List<AccountDTO>> getAccountsByType(@PathVariable Account.AccountType accountType) {
        List<AccountDTO> accounts = accountService.getAccountsByType(accountType);
        return ResponseEntity.ok(accounts);
    }
}
