package com.estore.estorebackend.billing.controller

import com.estore.estorebackend.billing.dto.OrderDTO;
import com.estore.estorebackend.billing.dto.OrderDetailDTO;
import com.estore.estorebackend.billing.dto.OrderStatusDTO;
import com.estore.estorebackend.billing.service.OrderProcessingService;
import com.estore.estorebackend.billing.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // Indispensable pour la communication avec Angular
public class OrderController {

    private final OrderService orderService;
    private final OrderProcessingService orderProcessingService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getUserOrders(@PathVariable Long userId) {
        List<OrderDTO> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailDTO> getOrderDetails(@PathVariable Long orderId) {
        OrderDetailDTO orderDetail = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderDetail);
    }

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<OrderDTO> checkoutCart(@PathVariable Long userId) {
        OrderDTO newOrder = orderProcessingService.placeOrder(userId);
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("/status")
    public ResponseEntity<Void> updateOrderStatus(@RequestBody OrderStatusDTO statusDTO) {
        orderProcessingService.updateStatus(statusDTO);
        return ResponseEntity.ok().build();
    }
}
