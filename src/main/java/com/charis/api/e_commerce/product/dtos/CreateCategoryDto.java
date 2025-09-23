package com.charis.api.e_commerce.product.dtos;

import com.charis.api.e_commerce.product.domain.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateCategoryDto {
    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    private UUID parentCategory;

    private Boolean isActive;


    public static Category fromDto(CreateCategoryDto dto, Category parentCategory) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        category.setParentCategory(parentCategory);
        return category;
    }
}
