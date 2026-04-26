package com.estore.estorebackend.reviews.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewCreateDTO {

    @NotNull(message = "L'utilisateur est obligatoire")
    private Long userId;

    @NotNull(message = "Le produit est obligatoire")
    private Long productId;

    @Min(value = 1, message = "La note doit être >= 1")
    @Max(value = 5, message = "La note doit être <= 5")
    private int rating;

    @Size(max = 500, message = "Le commentaire ne doit pas dépasser 500 caractères")
    private String comment;

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
