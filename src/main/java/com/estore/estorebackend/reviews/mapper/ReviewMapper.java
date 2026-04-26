package com.estore.estorebackend.reviews.mapper;

import com.estore.estorebackend.reviews.document.Review;
import com.estore.estorebackend.reviews.dto.ReviewDTO;
import com.estore.estorebackend.reviews.dto.ReviewResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public Review toEntity(ReviewDTO dto) {
        Review review = new Review();
        review.setUserId(dto.getUserId());
        review.setProductId(dto.getProductId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        return review;
    }

    public ReviewResponseDTO toResponseDTO(Review review) {
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(review.getId());
        dto.setUserId(review.getUserId());
        dto.setProductId(review.getProductId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        return dto;
    }
}
