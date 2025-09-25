package com.charis.api.e_commerce.product.repository;

import com.charis.api.e_commerce.product.domain.Category;
import com.charis.api.e_commerce.product.dtos.CreateCategoryDto;

import java.util.UUID;

public interface CategoryRepositoryCustom {
    Category updateCategory(UUID id, CreateCategoryDto cat);
}

