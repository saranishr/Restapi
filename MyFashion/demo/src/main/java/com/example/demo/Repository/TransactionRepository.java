package com.example.demo.Repository;

import com.example.demo.model.Transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Page<Transaction> findByTransactionDate(LocalDate transactionDate, Pageable pageable);
    Page<Transaction> findByTransactionAmount(Double transactionAmount, Pageable pageable);
    @Modifying
    @Query("SELECT a FROM Transaction a")
    List<Transaction> getAllTransactions();
}
