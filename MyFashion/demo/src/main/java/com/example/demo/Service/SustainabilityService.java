package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sustainability;
import com.example.demo.Repository.SustainabilityRepository;

@Service
public class SustainabilityService {

    private final SustainabilityRepository repo;
    private static final int MIN_ECO_SCORE = 5; // Define the minimum allowed ecoScore

    @Autowired
    public SustainabilityService(SustainabilityRepository repo) {
        this.repo = repo;
    }

    public Sustainability post(Sustainability c) {
        // Reject product if it has a very low ecoScore
        if (c.getEcoScore() < MIN_ECO_SCORE) {
            throw new IllegalArgumentException("Rejected: Product has a very low ecoScore. Consider improving its sustainability.");
        }

        // Assign a better ecoScore if the product has been resold multiple times
        if (c.getPastUsername() != null && !c.getPastUsername().isEmpty()) {
            c.setEcoScore(Math.max(c.getEcoScore(), MIN_ECO_SCORE + 2)); // Improve score by +2
        }

        return repo.save(c);
    }

    public List<Sustainability> findByEcoScore(int ecoScore) {
        return repo.findByEcoScore(ecoScore);
    }

    public List<Sustainability> findByPastUser(String pastUsername) {
        return repo.findByPastUsername(pastUsername);
    }

    public Page<Sustainability> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Optional<Sustainability> updateSustainability(int id, Sustainability user) {
        return repo.findById(id).map(existingUser -> {
            if (user.getEcoScore() != null) {
                existingUser.setEcoScore(user.getEcoScore());
            }
            if (user.getPastUsername() != null) {
                existingUser.setPastUsername(user.getPastUsername());
            }
            return repo.save(existingUser);
        });
    }

    public boolean deleteSustainability(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
