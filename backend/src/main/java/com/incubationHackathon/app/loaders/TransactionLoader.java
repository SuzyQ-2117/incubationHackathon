package com.incubationHackathon.app.loaders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubationHackathon.app.entities.Transaction;
import com.incubationHackathon.app.repos.TransactionRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class TransactionLoader {

    @Autowired
    private TransactionRepo transactionRepo;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void loadTransactionData() {
        try {
            File file = new File("src/main/resources/transactions.json");
            List<Transaction> transactions = objectMapper.readValue(file, new TypeReference<List<Transaction>>() {});
            transactionRepo.saveAll(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
