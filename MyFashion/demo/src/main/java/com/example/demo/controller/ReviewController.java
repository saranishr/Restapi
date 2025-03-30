package com.example.demo.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Service.ReviewService;
import com.example.demo.model.Review;
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review saved = service.post(review);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<Page<Review>> getAllReviews(Pageable pageable) {
        Page<Review> reviews = service.findAll(pageable);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/ratings/{ratings}")
    public ResponseEntity<List<Review>> findByRatings(@PathVariable long ratings) {
        List<Review> reviews = service.findByRatings(ratings);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/description/{reviewDescription}")
    public ResponseEntity<List<Review>> findByReviewDescription(@PathVariable String reviewDescription) {
        List<Review> reviews = service.findByReviewDescription(reviewDescription);
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable long id, @RequestBody Review review) {
        Optional<Review> updatedReview = service.updateReview(id, review);
        return updatedReview.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable long id) {
        boolean deleted = service.deleteReview(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}