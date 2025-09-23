package com.charis.api.e_commerce.product.mappers;

import com.charis.api.e_commerce.product.domain.Category;
import com.charis.api.e_commerce.product.dtos.CreateProductDto;
import com.charis.api.e_commerce.product.dtos.ProductDto;
import com.charis.api.e_commerce.product.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "price", target = "price", qualifiedByName = "centsToDollars")
    @Mapping(source = "category", target = "category", qualifiedByName = "categoryToId")
    ProductDto toDto(Product product);

    @Mapping(source = "price", target = "price", qualifiedByName = "dollarsToCents")
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(source = "price", target = "price", qualifiedByName = "dollarsToCents")
    Product toEntity(CreateProductDto dto);

    @Named("centsToDollars")
    default Double centsToDollars(Long cents) {
        return cents == null ? null : cents / 100.0;
    }

    @Named("dollarsToCents")
    default Long dollarsToCents(Double dollars) {
        return dollars == null ? null : Math.round(dollars * 100);
    }

    @Named("categoryToId")
    default UUID categoryToId(Category category) {
        return category == null ? null : category.getId();
    }
}

