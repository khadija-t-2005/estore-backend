package com.estore.estorebackend.reviews.controller;

import com.estore.estorebackend.reviews.dto.ReviewDTO;
import com.estore.estorebackend.reviews.dto.ReviewResponseDTO;
import com.estore.estorebackend.reviews.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(
            @Valid @RequestBody ReviewDTO reviewDTO) {

        ReviewResponseDTO created = reviewService.createReview(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByProduct(
            @PathVariable Long productId) {

        List<ReviewResponseDTO> reviews = reviewService.getReviewsByProduct(productId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByUser(
            @PathVariable Long userId) {

        List<ReviewResponseDTO> reviews = reviewService.getReviewsByUser(userId);
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
