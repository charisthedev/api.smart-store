package com.charis.api.e_commerce.product.repository;

import com.charis.api.e_commerce.common.exceptions.ResourceNotFoundException;
import com.charis.api.e_commerce.product.domain.Category;
import com.charis.api.e_commerce.product.dtos.CreateCategoryDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Category updateCategory(UUID id, CreateCategoryDto cat) {
        Category category = em.find(Category.class, id);
        if(category == null){
            throw new ResourceNotFoundException("category not found");
        }

        category.setName(cat.getName());
        category.setDescription(cat.getDescription());

        if (cat.getParentCategory() != null) {
            Category parentCategory = em.find(Category.class, cat.getParentCategory());
            if(parentCategory == null){
                throw new ResourceNotFoundException("parent category not found");
            }
            category.setParentCategory(parentCategory);
        }

        if (cat.getIsActive() != null) {
            category.setIsActive(cat.getIsActive());
        }

        return category;
    }
}

