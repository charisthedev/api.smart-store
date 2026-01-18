package com.charis.api.e_commerce.config;

import com.charis.api.e_commerce.payment.grpc.PaymentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @Value("${payment.service.grpc.host:localhost}")
    private String paymentServiceHost;

    @Value("${payment.service.grpc.port:50051}")
    private int paymentServicePort;

    @Bean
    public ManagedChannel paymentServiceChannel() {
        return ManagedChannelBuilder.forAddress(paymentServiceHost, paymentServicePort)
                .usePlaintext() // For production, use secure credentials
                .build();
    }

    @Bean
    public PaymentServiceGrpc.PaymentServiceBlockingStub paymentServiceBlockingStub(ManagedChannel paymentServiceChannel) {
        return PaymentServiceGrpc.newBlockingStub(paymentServiceChannel);
    }
}
