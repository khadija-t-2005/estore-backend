package com.estore.estorebackend.reviews.controller;

import com.estore.estorebackend.reviews.dto.ReviewDTO;
import com.estore.estorebackend.reviews.dto.ReviewResponseDTO;
import com.estore.estorebackend.reviews.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // ✅ Créer un avis
    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewResponseDTO response = reviewService.createReview(reviewDTO);
        return ResponseEntity.ok(response);
    }

    // ✅ Récupérer les avis d’un produit
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByProduct(@PathVariable Long productId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByProduct(productId);
        return ResponseEntity.ok(reviews);
    }

    // ✅ Récupérer les avis d’un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByUser(@PathVariable Long userId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByUser(userId);
        return ResponseEntity.ok(reviews);
    }

    // ✅ Supprimer un avis
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Avis supprimé avec succès");
    }
}
