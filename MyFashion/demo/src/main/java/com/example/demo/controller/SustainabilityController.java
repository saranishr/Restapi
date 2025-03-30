package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Service.SustainabilityService;
import com.example.demo.model.Sustainability;

@RestController
@RequestMapping("/api/sustainability")
public class SustainabilityController {

    private final SustainabilityService service;

    @Autowired
    public SustainabilityController(SustainabilityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createS(@RequestBody Sustainability sustain) {
        try {
            Sustainability saved = service.post(sustain);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<Sustainability>> getAll(Pageable pageable) {
        Page<Sustainability> sustainabilities = service.findAll(pageable);
        return ResponseEntity.ok(sustainabilities);
    }

    @GetMapping("/ecoScore/{ecoScore}")
    public ResponseEntity<List<Sustainability>> findByEcoScore(@PathVariable int ecoScore) {
        List<Sustainability> sustainabilities = service.findByEcoScore(ecoScore);
        return ResponseEntity.ok(sustainabilities);
    }

    @GetMapping("/pastUsername/{pastUsername}")
    public ResponseEntity<List<Sustainability>> findByPastUser(@PathVariable String pastUsername) {
        List<Sustainability> sustainabilities = service.findByPastUser(pastUsername);
        return ResponseEntity.ok(sustainabilities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sustainability> updateS(@PathVariable int id, @RequestBody Sustainability sustain) {
        Optional<Sustainability> updatedSustain = service.updateSustainability(id, sustain);
        return updatedSustain.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteS(@PathVariable int id) {
        boolean deleted = service.deleteSustainability(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
