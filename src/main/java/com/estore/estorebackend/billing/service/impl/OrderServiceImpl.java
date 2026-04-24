package com.estore.estorebackend.billing.service.impl;

import com.estore.estorebackend.billing.dto.OrderDTO;
import com.estore.estorebackend.billing.dto.OrderDetailDTO;
import com.estore.estorebackend.billing.entity.Order;
import com.estore.estorebackend.billing.mapper.OrderMapper;
import com.estore.estorebackend.billing.repository.OrderRepository;
import com.estore.estorebackend.billing.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserIdOrderByOrderDateDesc(userId)
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDetailDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec l'ID : " + orderId));
        return orderMapper.toDetailDTO(order);
    }
}
