package com.diepv.springsecuritydemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diepv.springsecuritydemo.dto.CategoryDTO;
import com.diepv.springsecuritydemo.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<?> addCategories(@RequestBody List<CategoryDTO> categories) {
        categoryService.addCategoryList(categories);
        return ResponseEntity.ok("add categories successful!");
    }

    @GetMapping(value = "/root")
    public ResponseEntity<?> getRootCategories() {
        return ResponseEntity.ok(categoryService.getRootCategories());
    }

    @GetMapping()
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/special")
    public ResponseEntity<?> getSpecialCategories() {
        return ResponseEntity.ok(categoryService.getSpecialCategories());
    }
}