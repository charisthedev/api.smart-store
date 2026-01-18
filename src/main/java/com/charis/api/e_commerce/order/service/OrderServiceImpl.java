package com.charis.api.e_commerce.order.service;

import com.charis.api.e_commerce.common.exceptions.ResourceNotFoundException;
import com.charis.api.e_commerce.order.domain.Orders;
import com.charis.api.e_commerce.order.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;

    @Override
    @Transactional
    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Orders getOrderById(UUID id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
    }

    @Override
    @Transactional
    public void confirmPayment(String paymentId) {
        Orders order = ordersRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for payment ID: " + paymentId));
        
        order.setStatus(OrderStatus.PAID);
        order.setPaymentStatus(PaymentStatus.SUCCESS);
        
        ordersRepository.save(order);
    }
}
