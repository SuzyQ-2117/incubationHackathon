package com.incubationHackathon.app.services;

import com.incubationHackathon.app.dtos.TransactionDTO;
import com.incubationHackathon.app.entities.Transaction;
import com.incubationHackathon.app.repos.TransactionRepository;
import com.incubationHackathon.app.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    private Transaction sampleTransaction;

    @BeforeEach
    public void setUp() {
        sampleTransaction = new Transaction();
        sampleTransaction.setTransactionId(1L);
        sampleTransaction.setTransactionType("DEB");
        sampleTransaction.setTimestamp(LocalDateTime.now().minusDays(10));
        sampleTransaction.setMerchantCode(123);
        sampleTransaction.setUserCategory("Groceries");
        sampleTransaction.setAmount(50.0f);
        // You may also want to set an Account object if required for testing
    }

    @Test
    public void testGetTransactionsWithinLast28Days() {
        //Arrange
        when(transactionRepository.findByTimestampBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(List.of(sampleTransaction));

        // Act
        List<TransactionDTO> result = transactionService.getTransactionsWithinLast28Days();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getTransactionId());
    }

    @Test
    public void testAddTransaction() {
        // Arrange
        when(transactionRepository.save(any(Transaction.class)))
                .thenReturn(sampleTransaction);

        // Act
        TransactionDTO result = transactionService.addTransaction(sampleTransaction);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getTransactionId());
        assertEquals("DEB", result.getTransactionType());
        assertEquals(50.0f, result.getAmount());
    }

    @Test
    public void testUpdateTransactionCategory() {
        // Arrange
        String newCategory = "Entertainment";
        sampleTransaction.setUserCategory(newCategory);
        when(transactionRepository.findById(1L))
                .thenReturn(Optional.of(sampleTransaction));
        when(transactionRepository.save(any(Transaction.class)))
                .thenReturn(sampleTransaction);

        // Act
        TransactionDTO result = transactionService.updateTransactionCategory(1L, newCategory);

        // Assert
        assertNotNull(result);
        assertEquals(newCategory, result.getUserCategory());
    }
}
