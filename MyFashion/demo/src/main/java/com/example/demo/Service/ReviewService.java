// ReviewService.java
package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Review;
import com.example.demo.Repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository repo;

    @Autowired
    public ReviewService(ReviewRepository repo) {
        this.repo = repo;
    }

    public Review post(Review review) {
        return repo.save(review);
    }

    public List<Review> findByRatings(long ratings) {
        return repo.findByRatings(ratings);
    }

    public List<Review> findByReviewDescription(String reviewDescription) {
        return repo.findByReviewDescription(reviewDescription);
    }

    public Page<Review> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Optional<Review> updateReview(long id, Review review) {
        return repo.findById(id).map(existingReview -> {
            if (review.getRatings() != 0) { // Check for non-default value instead of null
                existingReview.setRatings(review.getRatings());
            }
            if (review.getReviewDescription() != null && !review.getReviewDescription().isEmpty()) {
                existingReview.setReviewDescription(review.getReviewDescription());
            }
            return repo.save(existingReview);
        });
    }
    

    public boolean deleteReview(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
