package com.incubationHackathon.app.repos;

import com.incubationHackathon.app.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Custom query to find transactions within the last 28 days for a specific account ID
    List<Transaction> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
}
