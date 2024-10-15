package com.incubationHackathon.app.repos;

import com.incubationHackathon.app.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Custom method to find accounts by type
    List<Account> findByAccountType(Account.AccountType accountType);
}
