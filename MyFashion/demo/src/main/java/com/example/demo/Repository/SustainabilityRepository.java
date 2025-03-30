package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Sustainability;

public interface SustainabilityRepository extends JpaRepository<Sustainability, Integer> {
    List<Sustainability> findByEcoScore(int ecoScore);
    List<Sustainability> findByPastUsername(String pastUsername);
    Page<Sustainability> findAll(Pageable pageable);
}