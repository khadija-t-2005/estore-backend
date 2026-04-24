package com.estore.estorebackend.billing.service;

import com.estore.estorebackend.billing.dto.OrderDTO;
import com.estore.estorebackend.billing.dto.OrderDetailDTO;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrdersByUserId(Long userId);
    OrderDetailDTO getOrderById(Long orderId);
}
