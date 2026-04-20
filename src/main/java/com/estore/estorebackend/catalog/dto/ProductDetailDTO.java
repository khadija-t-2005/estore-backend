package com.estore.estorebackend.catalog.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDetailDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private CategoryDTO category;
}