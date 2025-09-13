package com.charis.api.e_commerce.product.controllers;

import com.charis.api.e_commerce.common.utils.ServerResult;
import com.charis.api.e_commerce.product.domain.Product;
import com.charis.api.e_commerce.product.dtos.ProductDto;
import com.charis.api.e_commerce.product.service.ProductService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Product> getProducts(){
        return this.productService.getProducts();
    }

    @GetMapping("/{id}")
    public ServerResult<ProductDto> getProductById(@PathVariable UUID id){
        return this.productService.getProductById(id);
    }

    @PostMapping()
    public void createProduct(@RequestBody Product prod){
        this.productService.createProduct(prod);
    }

    @PutMapping()
    public void updateProduct(@RequestBody Product prod){
        this.productService.updateProduct();
    }
}
