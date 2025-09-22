package com.charis.api.e_commerce.product.dtos;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateCategoryDto {
    @NotBlank
    private String name;
    private String description;
}
