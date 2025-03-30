package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Page<Transaction>> getAllTransactions(Pageable pageable) {
        Page<Transaction> transactions = transactionService.getAllTransactions(pageable);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Transaction>> searchTransactions(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String date,
            Pageable pageable) {
        Page<Transaction> transactions = transactionService.searchTransactions(userId, date, pageable);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction) {
        Transaction updatedTransaction = transactionService.updateTransaction(id, transaction);
        return updatedTransaction != null ? new ResponseEntity<>(updatedTransaction, HttpStatus.OK)
                                          : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id) {
        boolean isDeleted = transactionService.deleteTransaction(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                         : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
