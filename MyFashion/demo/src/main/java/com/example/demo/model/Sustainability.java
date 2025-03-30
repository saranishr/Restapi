package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Sustainability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer ecoScore;
    private String pastUsername;

   

    public Sustainability() {}

    public Sustainability(Integer ecoScore, String pastUsername) {
        this.ecoScore = ecoScore;
        this.pastUsername = pastUsername;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public Integer getEcoScore() {
        return ecoScore;
    }

    public void setEcoScore(Integer ecoScore) {
        this.ecoScore = ecoScore;
    }

    public String getPastUsername() {
        return pastUsername;
    }

    public void setPastUsername(String pastUsername) {
        this.pastUsername = pastUsername;
    }

   

  
}
