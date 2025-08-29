package com.charis.api.e_commerce.service.product;

import com.charis.api.e_commerce.models.product.Product;
import com.charis.api.e_commerce.models.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public List<Product> getProducts() {
        return this.productRepo.getProductList();
    }

    public Product getProductById(int id) {
        return this.productRepo.getProductById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
    }

    public void createProduct (Product prod) {
        this.productRepo.createProduct(prod);
    }
}
