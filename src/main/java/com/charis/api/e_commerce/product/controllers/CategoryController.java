package com.charis.api.e_commerce.product.controllers;

import com.charis.api.e_commerce.common.dtos.IdResponse;
import com.charis.api.e_commerce.common.utils.ServerResult;
import com.charis.api.e_commerce.product.dtos.CreateCategoryDto;
import com.charis.api.e_commerce.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<ServerResult<IdResponse>> createCategory(@Valid @RequestBody CreateCategoryDto body){
        return new ResponseEntity<ServerResult<IdResponse>>(categoryService.createCategory(body), HttpStatus.CREATED);
    }
}
