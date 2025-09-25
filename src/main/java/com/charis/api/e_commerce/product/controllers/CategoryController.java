package com.charis.api.e_commerce.product.controllers;

import com.charis.api.e_commerce.common.dtos.IdResponse;
import com.charis.api.e_commerce.common.utils.ServerResult;
import com.charis.api.e_commerce.product.domain.Category;
import com.charis.api.e_commerce.product.dtos.CreateCategoryDto;
import com.charis.api.e_commerce.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<ServerResult<List<Category>>> getCategories(){
        return new ResponseEntity<>(new ServerResult<List<Category>>("Successfully fetched list of categories",categoryService.getCategories()),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServerResult<Category>> getCategoryById(@PathVariable UUID id){
        return new ResponseEntity<>(new ServerResult<Category>("Successfully fetched category",categoryService.getCategoryById(id)),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ServerResult<IdResponse>> createCategory(@Valid @RequestBody CreateCategoryDto body){
        return new ResponseEntity<ServerResult<IdResponse>>(categoryService.createCategory(body), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServerResult> updateCategory(@PathVariable UUID id, @Valid @RequestBody CreateCategoryDto body){
        return new ResponseEntity<ServerResult>(categoryService.updateCategory(id,body),HttpStatus.OK);
    }
}
