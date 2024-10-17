package com.incubationHackathon.app.controllers;

import com.incubationHackathon.app.dtos.TransactionDTO;
import com.incubationHackathon.app.entities.Transaction;
import com.incubationHackathon.app.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRecentTransactions() {
        // Given
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        transactionDTOList.add(new TransactionDTO()); // Add dummy data if necessary
        when(transactionService.getTransactionsWithinLast28Days()).thenReturn(transactionDTOList);

        // When
        ResponseEntity<List<TransactionDTO>> response = transactionController.getRecentTransactions();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactionDTOList, response.getBody());
        verify(transactionService, times(1)).getTransactionsWithinLast28Days();
    }

    @Test
    public void testAddTransaction() {
        // Given
        Transaction transaction = new Transaction(); // Set necessary fields in Transaction if required
        TransactionDTO transactionDTO = new TransactionDTO(); // Set necessary fields in TransactionDTO if required
        when(transactionService.addTransaction(any(Transaction.class))).thenReturn(transactionDTO);

        // When
        ResponseEntity<TransactionDTO> response = transactionController.addTransaction(transaction);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactionDTO, response.getBody());
        verify(transactionService, times(1)).addTransaction(transaction);
    }

    @Test
    public void testUpdateTransactionCategory() {
        // Given
        Long transactionId = 1L;
        String newCategory = "NewCategory";
        TransactionDTO updatedTransactionDTO = new TransactionDTO(); // Set necessary fields in TransactionDTO if required
        when(transactionService.updateTransactionCategory(anyLong(), any(String.class))).thenReturn(updatedTransactionDTO);

        // When
        ResponseEntity<TransactionDTO> response = transactionController.updateTransactionCategory(transactionId, newCategory);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTransactionDTO, response.getBody());
        verify(transactionService, times(1)).updateTransactionCategory(transactionId, newCategory);
    }
}

