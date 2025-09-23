package com.charis.api.e_commerce.product.service;

import com.charis.api.e_commerce.common.dtos.IdResponse;
import com.charis.api.e_commerce.common.exceptions.ResourceNotFoundException;
import com.charis.api.e_commerce.common.utils.ServerResult;
import com.charis.api.e_commerce.product.dtos.CreateProductDto;
import com.charis.api.e_commerce.product.dtos.ProductDto;
import com.charis.api.e_commerce.product.domain.Product;
import com.charis.api.e_commerce.product.mappers.ProductMapper;
import com.charis.api.e_commerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ServerResult<IdResponse> createProduct(CreateProductDto dto) {
        Product product = productMapper.toEntity(dto);
        Product saved = productRepository.save(product);
        return new ServerResult<>("successfully created product",new IdResponse(saved.getId()));
    }

    public ServerResult<ProductDto> getProductById(UUID id){
        return productRepository.findById(id).map(product1 -> new ServerResult<ProductDto>("product retrieved successfully",productMapper.toDto(product1))).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public ServerResult<?> updateProduct(UUID id, ProductDto prod) {
        Product product = productMapper.toEntity(prod);
        this.updateProductRepo(id,product);
        return new ServerResult<>("product updated");
    }

    private Product updateProductRepo(UUID id, Product prod){
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("product not found"));
        product.setName(prod.getName());
        product.setPrice(prod.getPrice());
        return productRepository.save(product);
    }
}
