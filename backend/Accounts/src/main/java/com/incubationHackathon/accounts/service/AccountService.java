package com.incubationHackathon.accounts.service;

import com.incubationHackathon.accounts.dto.AccountDTO;
import com.incubationHackathon.accounts.entity.Account;
import com.incubationHackathon.accounts.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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

    public AccountDTO getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // If transactions are needed, make a call to Transaction-Service using RestTemplate
        // Example:
        // String url = "http://TRANSACTION-SERVICE/transactions/account/" + accountId;
        // List<TransactionDTO> transactions = restTemplate.getForObject(url, List.class);

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
                account.getSortCode(),
                account.getAccountNumber(),
                account.getProductCode(),
                account.getAccountType().toString(),
                account.getBalance(),
                account.getUserId()
        );
    }
}
