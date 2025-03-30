package com.example.demo.Service;

import com.example.demo.model.Transaction;
import com.example.demo.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    public Optional<Transaction> getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    public Page<Transaction> searchTransactions(Long userId, String date, Pageable pageable) {
        LocalDate transactionDate = (date != null) ? LocalDate.parse(date) : null;
        if (transactionDate != null) {
            return transactionRepository.findByTransactionDate(transactionDate, pageable);
        }
        return transactionRepository.findAll(pageable);
    }
   


    public Transaction updateTransaction(Integer id, Transaction transactionDetails) {
        return transactionRepository.findById(id).map(transaction -> {
            transaction.setTransactionDate(transactionDetails.getTransactionDate());
            transaction.setTransactionAmount(transactionDetails.getTransactionAmount());
            return transactionRepository.save(transaction);
        }).orElse(null);
    }

    public boolean deleteTransaction(Integer id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
