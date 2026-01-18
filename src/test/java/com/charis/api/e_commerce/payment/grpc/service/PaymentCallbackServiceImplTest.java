package com.charis.api.e_commerce.payment.grpc.service;

import com.charis.api.e_commerce.order.service.OrderService;
import com.charis.api.e_commerce.payment.grpc.PaymentCallbackResponse;
import com.charis.api.e_commerce.payment.grpc.PaymentSuccessEvent;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentCallbackServiceImplTest {

    @Mock
    private OrderService orderService;

    @Mock
    private StreamObserver<PaymentCallbackResponse> responseObserver;

    @InjectMocks
    private PaymentCallbackServiceImpl paymentCallbackService;

    @Test
    void onPaymentSuccess_shouldConfirmPaymentAndReturnSuccess() {
        // Given
        String paymentId = "payment-123";
        PaymentSuccessEvent request = PaymentSuccessEvent.newBuilder()
                .setPaymentId(paymentId)
                .setOrderId(UUID.randomUUID().toString())
                .setAmount(100.0)
                .build();

        // When
        paymentCallbackService.onPaymentSuccess(request, responseObserver);

        // Then
        verify(orderService).confirmPayment(paymentId);
        verify(responseObserver).onNext(argThat(PaymentCallbackResponse::getSuccess));
        verify(responseObserver).onCompleted();
    }

    @Test
    void onPaymentSuccess_shouldHandleExceptionAndReturnFailure() {
        // Given
        String paymentId = "payment-error";
        PaymentSuccessEvent request = PaymentSuccessEvent.newBuilder()
                .setPaymentId(paymentId)
                .build();

        doThrow(new RuntimeException("DB Error")).when(orderService).confirmPayment(paymentId);

        // When
        paymentCallbackService.onPaymentSuccess(request, responseObserver);

        // Then
        verify(orderService).confirmPayment(paymentId);
        verify(responseObserver).onNext(argThat(response -> !response.getSuccess()));
        verify(responseObserver).onCompleted();
    }
}
