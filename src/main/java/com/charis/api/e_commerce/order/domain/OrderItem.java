package com.charis.api.e_commerce.order.domain;

import com.charis.api.e_commerce.common.model.BaseEntity;
import com.charis.api.e_commerce.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_item")
public class OrderItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String productName; // snapshot

    @Column(nullable = false)
    private BigInteger unitPrice; // snapshot

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigInteger totalPrice;
}


