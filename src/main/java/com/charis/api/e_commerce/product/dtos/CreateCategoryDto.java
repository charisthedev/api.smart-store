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
}
