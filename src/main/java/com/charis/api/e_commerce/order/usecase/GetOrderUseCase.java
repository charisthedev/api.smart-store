package com.charis.api.e_commerce.order.usecase;

import com.charis.api.e_commerce.common.exceptions.UnAuthorizedAccessException;
import com.charis.api.e_commerce.identity.domain.User;
import com.charis.api.e_commerce.order.domain.Orders;
import com.charis.api.e_commerce.order.dtos.OrderResponse;
import com.charis.api.e_commerce.order.mappers.OrderMapper;
import com.charis.api.e_commerce.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetOrderUseCase {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Transactional(readOnly = true)
    public OrderResponse execute(User user, UUID orderId) {
        Orders order = orderService.getOrderById(orderId);

        if (!order.getUser().getId().equals(user.getId())) {
            throw new UnAuthorizedAccessException("You are not authorized to view this order");
        }

        return orderMapper.toOrderResponse(order);
    }
}
