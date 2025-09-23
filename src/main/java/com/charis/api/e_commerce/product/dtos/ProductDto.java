package com.charis.api.e_commerce.product.dtos;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    private Double price;
    private String name;
    private Integer quantity;
    private Set<String> images;
    private String thumbNail;
}

