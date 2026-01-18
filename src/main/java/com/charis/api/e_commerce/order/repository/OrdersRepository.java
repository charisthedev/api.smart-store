package com.charis.api.e_commerce.order.repository;

import com.charis.api.e_commerce.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID>, OrdersRepositoryCustom {
}
