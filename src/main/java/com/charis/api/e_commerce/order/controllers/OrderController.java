package com.charis.api.e_commerce.order.controllers;

import com.charis.api.e_commerce.identity.domain.User;
import com.charis.api.e_commerce.order.dtos.CreateOrderRequest;
import com.charis.api.e_commerce.order.dtos.OrderResponse;
import com.charis.api.e_commerce.order.usecase.CreateOrderUseCase;
import com.charis.api.e_commerce.order.usecase.GetOrderUseCase;
import com.charis.api.e_commerce.security.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @AuthenticationPrincipal UserPrincipal currentUser, 
            @Valid @RequestBody CreateOrderRequest request) {
        OrderResponse response = createOrderUseCase.execute(currentUser.getUser(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(
            @AuthenticationPrincipal UserPrincipal currentUser,
            @PathVariable UUID id) {
        OrderResponse response = getOrderUseCase.execute(currentUser.getUser(), id);
        return ResponseEntity.ok(response);
    }
}
