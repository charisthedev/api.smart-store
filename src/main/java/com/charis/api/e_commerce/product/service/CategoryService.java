package com.charis.api.e_commerce.product.service;

import com.charis.api.e_commerce.common.exceptions.ResourceNotFoundException;
import com.charis.api.e_commerce.product.domain.Category;
import com.charis.api.e_commerce.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(UUID id){
        return categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category not found"));
    }

    public
}
