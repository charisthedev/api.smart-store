package com.charis.api.e_commerce.product.controllers;

import com.charis.api.e_commerce.common.dtos.IdResponse;
import com.charis.api.e_commerce.common.utils.ServerResult;
import com.charis.api.e_commerce.product.dtos.ProductDto;
import com.charis.api.e_commerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public List<ProductDto> getProducts(){
        return this.productService.getProducts();
    }

    @GetMapping("/{id}")
    public ServerResult<ProductDto> getProductById(@PathVariable UUID id){
        return this.productService.getProductById(id);
    }

    @PostMapping()
    public ServerResult<IdResponse> createProduct(@RequestBody ProductDto prod){
        return this.productService.createProduct(prod);
    }

    @PutMapping("/{id}")
    public ServerResult updateProduct(@RequestBody ProductDto prod, @PathVariable UUID id){
        return this.productService.updateProduct(id,prod);
    }
}
