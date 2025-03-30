package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class ClothingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String size;

@ManyToOne
@JoinColumn(name = "user_id")
@JsonIgnoreProperties("clothingItems") // Ignore back reference
private User user;

@OneToMany(mappedBy = "clothingItem", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnoreProperties("clothingItem") // Ignore back reference
private List<Transaction> transactions = new ArrayList<>();


    // Constructors
    public ClothingItem() {}

    public ClothingItem(String description, String size, User user) {
        this.description = description;
        this.size = size;
        this.user = user;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClothingItem)) return false;
        ClothingItem that = (ClothingItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
