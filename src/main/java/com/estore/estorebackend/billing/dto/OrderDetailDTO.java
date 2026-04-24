package com.estore.estorebackend.billing.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDetailDTO extends OrderDTO {
    private String customerEmail;
    private List<OrderItemDTO> items;
}
