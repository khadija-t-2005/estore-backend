package com.estore.estorebackend.billing.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private String status;
}
