package com.charis.api.e_commerce.product.service;

import com.charis.api.e_commerce.common.dtos.IdResponse;
import com.charis.api.e_commerce.common.exceptions.ResourceNotFoundException;
import com.charis.api.e_commerce.common.utils.ServerResult;
import com.charis.api.e_commerce.product.domain.Category;
import com.charis.api.e_commerce.product.domain.Product;
import com.charis.api.e_commerce.product.dtos.CreateCategoryDto;
import com.charis.api.e_commerce.product.mappers.CategoryMapper;
import com.charis.api.e_commerce.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<Category> getCategories(){
        return categoryRepository.findAll().stream().takeWhile(Category::getIsActive).collect(Collectors.toList());
    }

    public Category getCategoryById(UUID id){
        return categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category not found"));
    }

    public ServerResult<IdResponse> createCategory(CreateCategoryDto dto){
        Category category = categoryRepository.save(categoryMapper.toEntity(dto));
        return new ServerResult<IdResponse>("category created successfully",new IdResponse(category.getId()));
    }

    public ServerResult updateCategory(UUID id, CreateCategoryDto body){
        categoryRepository.updateCategory(id,body);
        return new ServerResult("category updated successfully");
    }
}
