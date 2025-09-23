package com.charis.api.e_commerce.product.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Set;
import java.util.UUID;

public class CreateProductDto {
    @NotNull
    @DecimalMin(value = "0.01",inclusive = true,message = "Price must be greater than 0")
    private Double price;

    @NotEmpty
    private String name;

    @NotNull
    private String thumbNail;

    @PositiveOrZero
    private Integer quantity;

    @NotNull(message = "product category is required")
    private UUID category;

    private Set<String> images;
}
