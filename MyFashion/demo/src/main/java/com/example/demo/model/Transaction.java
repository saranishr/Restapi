package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate transactionDate;
    private Double transactionAmount;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("transactions")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "clothingitem_id")
    @JsonIgnoreProperties("transactions")
    private ClothingItem clothingItem;
    


    // Constructors
    public Transaction() {}

    public Transaction(LocalDate transactionDate, Double transactionAmount, User user, ClothingItem clothingItem) {
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.user = user;
        this.clothingItem = clothingItem;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClothingItem getClothingItem() {
        return clothingItem;
    }

    public void setClothingItem(ClothingItem clothingItem) {
        this.clothingItem = clothingItem;
    }
}
