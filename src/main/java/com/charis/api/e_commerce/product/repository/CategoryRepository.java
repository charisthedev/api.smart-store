package com.charis.api.e_commerce.product.repository;

import com.charis.api.e_commerce.product.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>, CategoryRepositoryCustom {
}
