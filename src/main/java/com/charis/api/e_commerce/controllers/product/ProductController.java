package com.charis.api.e_commerce.controllers.product;

import com.charis.api.e_commerce.models.product.Product;
import com.charis.api.e_commerce.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Product getProductById(@PathVariable int id){
        return this.productService.getProductById(id);
    }

    @PostMapping()
    public void createProduct(Product prod){
        this.productService.createProduct(prod);
    }
}
