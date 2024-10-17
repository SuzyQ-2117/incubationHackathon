package com.incubationHackathon.accounts.service;

import com.incubationHackathon.accounts.dto.AccountDTO;
import com.incubationHackathon.accounts.entity.Account;
import com.incubationHackathon.accounts.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AccountDTO addAccount(Account account) {
        System.out.println("Checking account: " + account.getProductCode());

        // Check if an account with the same productCode already exists for the user
        Optional<Account> existingAccount = getAccountByProductCode(account.getProductCode(), account.getUserId());

        if (existingAccount.isPresent()) {
            System.out.println("Account already exists for user ID: " + account.getUserId() + " with product code: " + account.getProductCode());
            throw new RuntimeException("Account already exists for this user");
        }

        // Save the account as it does not exist for this user
        Account savedAccount = accountRepository.save(account);
        return convertToDTO(savedAccount);
    }

    public Optional<Account> getAccountByProductCode(String productCode, Long userId) {
        return accountRepository.findByProductCode(productCode)
                .filter(account -> account.getUserId().equals(userId));
    }


    public void deleteAccount(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new RuntimeException("Account not found");
        }
        accountRepository.deleteById(accountId);
    }

    public List<AccountDTO> getAccountsByType(Account.AccountType accountType) {
        return accountRepository.findByAccountType(accountType)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Retrieve all accounts by user ID
    public List<AccountDTO> getAccountsByUserId(Long userId) {
        return accountRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Check if an account is owned by a specific user
    public boolean isAccountOwnedByUser(Long accountId, Long userId) {
        return accountRepository.findById(accountId)
                .map(account -> account.getUserId().equals(userId))
                .orElse(false);
    }

    private AccountDTO convertToDTO(Account account) {
        return new AccountDTO(
                account.getSortCode(),
                account.getAccountNumber(),
                account.getProductCode(),
                account.getAccountType().toString(),
                account.getBalance(),
                account.getUserId()
        );
    }
}
