package com.estore.estorebackend.reviews.imp;

import com.estore.estorebackend.reviews.document.Review;
import com.estore.estorebackend.reviews.dto.ReviewDTO;
import com.estore.estorebackend.reviews.dto.ReviewResponseDTO;
import com.estore.estorebackend.reviews.mapper.ReviewMapper;
import com.estore.estorebackend.reviews.repository.ReviewRepository;
import com.estore.estorebackend.reviews.service.ReviewService;
import com.estore.estorebackend.shared.exception.ResourceNotFoundException;
import com.estore.estorebackend.shared.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewResponseDTO createReview(ReviewDTO reviewDTO) {

        if (reviewRepository.existsByUserIdAndProductId(
                reviewDTO.getUserId(), reviewDTO.getProductId())) {
            throw new ValidationException("Vous avez déjà laissé un avis pour ce produit.");
        }

        Review review = reviewMapper.toEntity(reviewDTO);
        Review saved = reviewRepository.save(review);

        return reviewMapper.toResponseDTO(saved);
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .map(reviewMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId)
                .stream()
                .map(reviewMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReview(String reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Avis introuvable avec l'id : " + reviewId));

        reviewRepository.delete(review);
    }
}
