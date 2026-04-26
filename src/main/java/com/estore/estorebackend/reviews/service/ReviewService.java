package com.estore.estorebackend.reviews.service;

import com.estore.estorebackend.reviews.dto.ReviewDTO;
import com.estore.estorebackend.reviews.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {

    ReviewResponseDTO createReview(ReviewDTO reviewDTO);

    List<ReviewResponseDTO> getReviewsByProduct(Long productId);

    List<ReviewResponseDTO> getReviewsByUser(Long userId);

    void deleteReview(String reviewId);
}
