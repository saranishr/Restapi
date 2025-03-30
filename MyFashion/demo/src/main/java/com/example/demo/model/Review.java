package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Review {

    @Id
    private long id;
    private String reviewDescription;
    private long ratings;

    // Default constructor
    public Review() {}

    // Constructor with id and reviewDescription
    public Review(long id, String reviewDescription) {
        this.id = id;
        this.reviewDescription = reviewDescription;
    }

    // Constructor with ratings
    public Review(long ratings) {
        this.ratings = ratings;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public long getRatings() {
        return ratings;
    }

    public void setRatings(long ratings) {
        this.ratings = ratings;
    }
}
