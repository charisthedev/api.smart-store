package com.charis.api.e_commerce.order.usecase;

import com.charis.api.e_commerce.identity.domain.User;
import com.charis.api.e_commerce.order.domain.OrderItem;
import com.charis.api.e_commerce.order.domain.Orders;
import com.charis.api.e_commerce.order.domain.OrderStatus;
import com.charis.api.e_commerce.order.domain.PaymentStatus;
import com.charis.api.e_commerce.order.dtos.CreateOrderRequest;
import com.charis.api.e_commerce.order.dtos.OrderResponse;
import com.charis.api.e_commerce.order.mappers.OrderMapper;
import com.charis.api.e_commerce.order.service.OrderService;
import com.charis.api.e_commerce.product.domain.Product;
import com.charis.api.e_commerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderService orderService;
    private final ProductService productService;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderResponse execute(User user, CreateOrderRequest request) {
        log.info("Creating order for user: {}", user.getId());

        List<OrderItem> orderItems = new ArrayList<>();
        BigInteger subtotal = BigInteger.ZERO;

        for (CreateOrderRequest.OrderItemRequest itemRequest : request.getItems()) {
            Product product = productService.getProductEntity(itemRequest.getProductId());

            // Basic inventory check (expand as needed)
            if (product.getQuantity() < itemRequest.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }

            BigInteger itemPrice = BigInteger.valueOf(product.getPrice());
            BigInteger itemTotal = itemPrice.multiply(BigInteger.valueOf(itemRequest.getQuantity()));
            subtotal = subtotal.add(itemTotal);

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .productName(product.getName())
                    .quantity(itemRequest.getQuantity())
                    .unitPrice(itemPrice)
                    .totalPrice(itemTotal)
                    .build();
            orderItems.add(orderItem);
        }

        // Simple tax/shipping logic (can be expanded)
        BigInteger tax = BigInteger.ZERO; // e.g. subtotal.multiply(BigInteger.valueOf(10)).divide(BigInteger.valueOf(100));
        BigInteger shippingFee = BigInteger.ZERO; 
        BigInteger total = subtotal.add(tax).add(shippingFee);

        Orders order = Orders.builder()
                .user(user)
                .status(OrderStatus.PENDING)
                .paymentStatus(PaymentStatus.PENDING)
                .subtotal(subtotal)
                .tax(tax)
                .shippingFee(shippingFee)
                .total(total)
                .items(new ArrayList<>()) 
                .build();
        
        // Associated items with order
        for(OrderItem item : orderItems) {
            item.setOrder(order);
            order.getItems().add(item);
        }

        Orders savedOrder = orderService.createOrder(order);
        log.info("Order created successfully with ID: {}", savedOrder.getId());

        return orderMapper.toOrderResponse(savedOrder);
    }
}
