package com.charis.api.e_commerce.order.service;

import com.charis.api.e_commerce.order.domain.Orders;
import java.util.UUID;

public interface OrderService {
    Orders createOrder(Orders order);
    Orders getOrderById(UUID id);
    void confirmPayment(String paymentId);
}
