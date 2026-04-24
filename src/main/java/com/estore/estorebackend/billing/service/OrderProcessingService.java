package com.estore.estorebackend.billing.service.impl;

import com.estore.estorebackend.billing.dto.OrderDTO;
import com.estore.estorebackend.billing.dto.OrderStatusDTO;
import com.estore.estorebackend.billing.entity.Order;
import com.estore.estorebackend.billing.repository.OrderRepository;
import com.estore.estorebackend.billing.service.OrderProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderProcessingServiceImpl implements OrderProcessingService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderDTO placeOrder(Long userId) {
        // La logique pour transformer le Cart en Order viendra ici plus tard
        return null;
    }

    @Override
    @Transactional
    public void updateStatus(OrderStatusDTO statusDTO) {
        Order order = orderRepository.findById(statusDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec l'ID : " + statusDTO.getOrderId()));

        order.setStatus(statusDTO.getStatus());
        orderRepository.save(order);
    }
}
