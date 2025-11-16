package com.charis.api.e_commerce.product.repository;

import com.charis.api.e_commerce.product.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID>, CategoryRepositoryCustom {
}
