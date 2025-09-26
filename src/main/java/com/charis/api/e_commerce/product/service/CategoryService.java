package com.charis.api.e_commerce.product.service;

import com.charis.api.e_commerce.common.dtos.IdResponse;
import com.charis.api.e_commerce.common.dtos.KeySetPaginationResponseDto;
import com.charis.api.e_commerce.common.dtos.KeySetPaginationSearchDto;
import com.charis.api.e_commerce.common.exceptions.ResourceNotFoundException;
import com.charis.api.e_commerce.common.service.KeySetPaginator;
import com.charis.api.e_commerce.common.utils.ServerResult;
import com.charis.api.e_commerce.product.domain.Category;
import com.charis.api.e_commerce.product.dtos.CreateCategoryDto;
import com.charis.api.e_commerce.product.mappers.CategoryMapper;
import com.charis.api.e_commerce.product.repository.CategoryRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final KeySetPaginator keysetPaginator;

    public KeySetPaginationResponseDto<Category> getCategories(KeySetPaginationSearchDto dto){
        return this.keysetPaginator.paginate(Category.class,dto.getSortBy(),dto.getSort(),dto.getCursor(),dto.getLimitSafe(dto),(root, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), true));

            if (dto.getSearch() != null && !dto.getSearch().isBlank()) {
                predicates.add(
                        cb.like(cb.lower(root.get("name")), "%" + dto.getSearch().toLowerCase() + "%")
                );
            }

            return predicates;
        });
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
