package com.charis.api.e_commerce.models.product;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
@Repository
public class ProductRepository {
    List<Product> productList= new ArrayList<>(Arrays.asList(new Product(101, "Iphone", 50000), new Product(102, "Canon Camera", 70000), new Product(103, "Shure Mic", 10000)));;

    public Optional<Product> getProductById(int id) {
        return productList.stream().filter(p -> p.getId() == id).findFirst();
    }

    public void createProduct (Product prod) {
        productList.add(prod);
    }
}
