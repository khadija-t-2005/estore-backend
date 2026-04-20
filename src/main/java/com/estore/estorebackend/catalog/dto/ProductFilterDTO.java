package com.estore.estorebackend.catalog.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductFilterDTO {
    private String name;
    private Long categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer page;
    private Integer size;
}