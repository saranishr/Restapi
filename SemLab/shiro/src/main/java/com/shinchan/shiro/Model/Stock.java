package com.shinchan.shiro.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private int quantity;
@ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;
    public Stock() {}

    public Stock(String location, int quantity, Product product) {
        this.location = location;
        this.quantity = quantity;
          this.product = product;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public void setId(Long id) {
        this.id = id;
    }
}
