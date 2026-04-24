package com.estore.estorebackend.billing.service;

import com.estore.estorebackend.billing.dto.OrderDTO;
import com.estore.estorebackend.billing.dto.OrderStatusDTO;

public interface OrderProcessingService {
    OrderDTO placeOrder(Long userId);
    void updateStatus(OrderStatusDTO statusDTO);
}
