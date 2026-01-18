package com.charis.api.e_commerce.order.service;

import com.charis.api.e_commerce.order.dtos.OrderResponse;
import com.charis.api.e_commerce.payment.grpc.PaymentRequest;
import com.charis.api.e_commerce.payment.grpc.PaymentResponse;
import com.charis.api.e_commerce.payment.grpc.PaymentServiceGrpc;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentGrpcClient {

    private final PaymentServiceGrpc.PaymentServiceBlockingStub paymentServiceStub;

    public String initiatePayment(OrderResponse order) {
        log.info("Initiating payment via gRPC for order: {}", order.getId());

        PaymentRequest request = PaymentRequest.newBuilder()
                .setOrderId(order.getId().toString())
                .setAmount(order.getTotal().doubleValue())
                .setUserId(order.getUserId().toString())
                .setCurrency("USD") // Assuming USD for now, or fetch from order if available
                // .setCurrency(order.getCurrency()) 
                .build();

        try {
            PaymentResponse response = paymentServiceStub.initiatePayment(request);
            log.info("Payment initiated successfully for order {}. Payment ID: {}, Status: {}", 
                     order.getId(), response.getPaymentId(), response.getStatus());
            return response.getPaymentId();
        } catch (StatusRuntimeException e) {
            log.error("Failed to initiate payment for order {}. gRPC Status: {}", order.getId(), e.getStatus(), e);
            // Handle error (e.g., throw custom exception, retry, etc.)
            // For now, valid to just log as the process might be async or reconciled later
            return null;
        }
    }
}
