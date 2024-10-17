package com.incubationHackathon.accounts.repo;

import com.incubationHackathon.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAll();
    List<Account> findByUserId(Long userId);
    List<Account> findByAccountType(Account.AccountType accountType);
}
