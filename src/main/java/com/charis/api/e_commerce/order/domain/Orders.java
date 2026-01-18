package com.charis.api.e_commerce.order.domain;

import com.charis.api.e_commerce.common.model.BaseEntity;
import com.charis.api.e_commerce.identity.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Orders extends BaseEntity {
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(nullable = false)
    private BigInteger subtotal;

    @Builder.Default
    @Column(nullable = false)
    private BigInteger tax = BigInteger.ZERO;

    @Builder.Default
    @Column(nullable = false)
    private BigInteger shippingFee = BigInteger.ZERO;

    @Column(nullable = false)
    private BigInteger total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> items = new ArrayList<>();
}

