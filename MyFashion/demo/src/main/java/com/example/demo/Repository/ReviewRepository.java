package com.example.demo.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findByReviewDescription(String reviewDescription);
    public List<Review> findByRatings(long ratings);
    
}
