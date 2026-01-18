package com.charis.api.e_commerce.order.dtos;

import com.charis.api.e_commerce.order.domain.OrderStatus;
import com.charis.api.e_commerce.order.domain.PaymentStatus;
import lombok.Builder;
import lombok.Data;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class OrderResponse {
    private UUID id;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private BigInteger subtotal;
    private BigInteger tax;
    private BigInteger shippingFee;
    private BigInteger total;
    private Instant createdAt;
    private List<OrderItemResponse> items;

    @Data
    @Builder
    public static class OrderItemResponse {
        private UUID productId;
        private String productName;
        private BigInteger price;
        private Integer quantity;
    }
}
