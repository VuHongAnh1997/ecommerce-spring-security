package com.diepv.springsecuritydemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diepv.springsecuritydemo.dto.ProductDTO;
import com.diepv.springsecuritydemo.payload.request.PaginationAndSortingRequest;
import com.diepv.springsecuritydemo.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @GetMapping("/featured")
    public ResponseEntity<List<ProductDTO>> getProductByIsFeatured(@RequestParam boolean isFeatured) {
        return ResponseEntity.ok().body(productService.getProductByIsFeatured(isFeatured));
    }

    @GetMapping("/category")
    public ResponseEntity<Map<String, Object>> findByCategory(
            @ModelAttribute PaginationAndSortingRequest paginationAndSortingRequest) {

        try {
            Map<String, Object> response = new HashMap<>();
            response = productService.getProductsByCategory(paginationAndSortingRequest);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> addProducts(@RequestBody List<ProductDTO> products) {
        productService.addProducts(products);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/special")
    public ResponseEntity<?> getProductsBySpecialCategory(@RequestParam Long categoryId) {
        List<ProductDTO> products = productService.getProductsBySpecialCategory(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
