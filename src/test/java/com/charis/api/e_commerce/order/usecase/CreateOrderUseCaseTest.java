package com.charis.api.e_commerce.order.usecase;

import com.charis.api.e_commerce.identity.domain.User;
import com.charis.api.e_commerce.order.domain.Orders;
import com.charis.api.e_commerce.order.dtos.CreateOrderRequest;
import com.charis.api.e_commerce.order.dtos.OrderResponse;
import com.charis.api.e_commerce.order.mappers.OrderMapper;
import com.charis.api.e_commerce.order.service.OrderService;
import com.charis.api.e_commerce.order.service.PaymentGrpcClient;
import com.charis.api.e_commerce.product.domain.Product;
import com.charis.api.e_commerce.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {

    @Mock
    private OrderService orderService;

    @Mock
    private ProductService productService;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private PaymentGrpcClient paymentGrpcClient;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    @Test
    void execute_shouldCreateOrderAndInitiatePayment() {
        // Given
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);

        UUID productId = UUID.randomUUID();
        CreateOrderRequest.OrderItemRequest itemRequest = new CreateOrderRequest.OrderItemRequest();
        itemRequest.setProductId(productId);
        itemRequest.setQuantity(1);
        
        CreateOrderRequest request = new CreateOrderRequest();
        request.setItems(List.of(itemRequest));

        Product product = new Product();
        product.setId(productId);
        product.setPrice(100L);
        product.setQuantity(10);
        product.setName("Test Product");

        Orders order = Orders.builder()
                .user(user)
                .total(BigInteger.valueOf(100))
                .build();
        order.setId(UUID.randomUUID());

        OrderResponse response = OrderResponse.builder()
                .id(order.getId())
                .userId(userId)
                .total(BigInteger.valueOf(100))
                .build();

        when(productService.getProductEntity(productId)).thenReturn(product);
        when(orderService.createOrder(any(Orders.class))).thenReturn(order);
        when(orderMapper.toOrderResponse(order)).thenReturn(response);

        when(paymentGrpcClient.initiatePayment(response)).thenReturn("payment-123");

        // When
        createOrderUseCase.execute(user, request);

        // Then
        verify(orderService, org.mockito.Mockito.times(2)).createOrder(any(Orders.class));
        verify(paymentGrpcClient).initiatePayment(response);
        verify(orderService).createOrder(org.mockito.ArgumentMatchers.argThat(o -> 
            o.getPaymentId() != null && o.getPaymentId().equals("payment-123")
        ));
    }
}
