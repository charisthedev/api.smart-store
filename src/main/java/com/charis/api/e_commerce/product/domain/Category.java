package com.charis.api.e_commerce.product.domain;

import com.charis.api.e_commerce.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "Text")
    private String description;

    @Column(nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> subCategories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parentCategory;
}
