package com.charis.api.e_commerce.product.mappers;

import com.charis.api.e_commerce.product.dtos.ProductDto;
import com.charis.api.e_commerce.product.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "price", target = "price", qualifiedByName = "centsToDollars")
    ProductDto toDto(Product product);

    @Mapping(source = "price", target = "price", qualifiedByName = "dollarsToCents")
    Product toEntity(ProductDto dto);

    @Named("centsToDollars")
    default Double centsToDollars(Long cents) {
        return cents == null ? null : cents / 100.0;
    }

    @Named("dollarsToCents")
    default Long dollarsToCents(Double dollars) {
        return dollars == null ? null : Math.round(dollars * 100);
    }
}

