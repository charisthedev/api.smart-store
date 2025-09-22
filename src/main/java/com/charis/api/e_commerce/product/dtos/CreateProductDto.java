package com.charis.api.e_commerce.product.dtos;

import jakarta.validation.constraints.NotEmpty;

public class CreateProductDto {
    @NotEmpty
    private Double price;
    private String name;
}
