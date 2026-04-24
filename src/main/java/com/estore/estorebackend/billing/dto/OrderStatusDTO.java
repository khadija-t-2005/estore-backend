package com.estore.estorebackend.billing.dto;

import lombok.Data;

@Data
public class OrderStatusDTO {
    private Long orderId;
    private String status;
}
