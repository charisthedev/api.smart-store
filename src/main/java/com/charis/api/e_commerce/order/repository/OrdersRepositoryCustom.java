package com.charis.api.e_commerce.order.repository;

import com.charis.api.e_commerce.order.domain.OrderStatus;
import com.charis.api.e_commerce.order.domain.Orders;

import java.util.List;
import java.util.UUID;

public interface OrdersRepositoryCustom {
    void updateOrderStatusById(UUID id, OrderStatus status);
    List<Orders> findUsersOrders(UUID userId);
}
