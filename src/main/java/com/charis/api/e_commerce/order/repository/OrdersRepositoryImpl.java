package com.charis.api.e_commerce.order.repository;

import com.charis.api.e_commerce.order.domain.OrderStatus;
import com.charis.api.e_commerce.order.domain.Orders;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public class OrdersRepositoryImpl implements OrdersRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void updateOrderStatusById(UUID id, OrderStatus status) {
        em.createQuery("UPDATE Orders o SET o.status = :status WHERE o.id = :id")
                .setParameter("status", status)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<Orders> findUsersOrders(UUID userId) {
        return em.createQuery("SELECT o FROM Orders o WHERE o.user.id = :userId", Orders.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
