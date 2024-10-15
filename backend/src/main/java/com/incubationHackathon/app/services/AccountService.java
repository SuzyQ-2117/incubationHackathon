package com.incubationHackathon.app.services;

import com.incubationHackathon.app.dtos.AccountDTO;
import com.incubationHackathon.app.entities.Account;
import com.incubationHackathon.app.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AccountDTO getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return convertToDTO(account);
    }

    public AccountDTO addAccount(Account account) {
        account.setProductCode(account.getSortCode() + account.getAccountNumber());
        Account savedAccount = accountRepository.save(account);
        return convertToDTO(savedAccount);
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

    private AccountDTO convertToDTO(Account account) {
        return new AccountDTO(
                account.getAccountId(),
                account.getSortCode(),
                account.getAccountNumber(),
                account.getProductCode(),
                account.getAccountType().toString(),
                account.getBalance()
        );
    }
}
