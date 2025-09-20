package com.charis.api.e_commerce.product.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    private Double price;
    private String name;
}

