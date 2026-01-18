package com.charis.api.e_commerce.payment.grpc.service;

import com.charis.api.e_commerce.order.service.OrderService;
import com.charis.api.e_commerce.payment.grpc.PaymentCallbackResponse;
import com.charis.api.e_commerce.payment.grpc.PaymentCallbackServiceGrpc;
import com.charis.api.e_commerce.payment.grpc.PaymentSuccessEvent;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentCallbackServiceImpl extends PaymentCallbackServiceGrpc.PaymentCallbackServiceImplBase {

    private final OrderService orderService;

    @Override
    public void onPaymentSuccess(PaymentSuccessEvent request, StreamObserver<PaymentCallbackResponse> responseObserver) {
        log.info("Received Payment Success Callback. PaymentID: {}, OrderID: {}, Amount: {}", 
                 request.getPaymentId(), request.getOrderId(), request.getAmount());

        try {
            orderService.confirmPayment(request.getPaymentId());
            
            PaymentCallbackResponse response = PaymentCallbackResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Payment confirmed successfully")
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error processing payment callback", e);
            
            PaymentCallbackResponse response = PaymentCallbackResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Failed to process payment confirmation: " + e.getMessage())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
