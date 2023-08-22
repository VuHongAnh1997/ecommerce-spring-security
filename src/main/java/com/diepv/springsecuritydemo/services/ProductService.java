package com.diepv.springsecuritydemo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.diepv.springsecuritydemo.dto.CategoryDTO;
import com.diepv.springsecuritydemo.dto.ProductDTO;
import com.diepv.springsecuritydemo.mapper.ProductMapper;
import com.diepv.springsecuritydemo.models.Product;
import com.diepv.springsecuritydemo.payload.request.PaginationAndSortingRequest;
import com.diepv.springsecuritydemo.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return productMapper.mapToProductDTOList(products);
    }

    public void addProducts(List<ProductDTO> productDTOs) {
        productRepository.saveAll(productMapper.mapToProductList(productDTOs));
    }

    public List<ProductDTO> getProductByIsFeatured(boolean isFeatured) {
        List<Product> products = productRepository.findByIsFeatured(isFeatured);
        return productMapper.mapToProductDTOList(products);
    }

    public Map<String, Object> getProductsByCategory(PaginationAndSortingRequest request) {

        List<Order> orders = new ArrayList<Order>();
        Pageable paging;

        if (request.getSortByList() != null) {

            orders.add(new Order(getSortDirection(request.getSortOrderList().get(0)),
                    request.getSortByList().get(0)));
            paging = PageRequest.of(request.getPage(), request.getSize(), Sort.by(orders));
        } else {
            paging = PageRequest.of(request.getPage(), request.getSize());
        }

        List<Product> products = new ArrayList<Product>();

        Page<Product> pageProducts;

        CategoryDTO categoryDTO = categoryService.findById(request.getCategoryId());

        List<CategoryDTO> categoriesLevel3 = new ArrayList<>();
        if (categoryDTO.getLevel() == 1) {
            List<CategoryDTO> categoriesLevel2 = new ArrayList<>();
            categoriesLevel2 = categoryService.findByParentId(request.getCategoryId());
            if (categoriesLevel2.size() > 0) {
                for (int i = 0; i < categoriesLevel2.size(); i++) {
                    List<CategoryDTO> categories = categoryService.findByParentId(categoriesLevel2.get(i).getId());
                    if (categories.size() > 0) {
                        categoriesLevel3.addAll(categories);
                    }
                }
            }
            List<Long> categoryIds = categoriesLevel3.stream().map(category -> {
                return category.getId();
            }).collect(Collectors.toList());
            pageProducts = productRepository.findByCategory_IdIn(categoryIds, paging);
        } else if (categoryDTO.getLevel() == 2) {
            categoriesLevel3 = categoryService.findByParentId(categoryDTO.getId());
            List<Long> categoryIds = categoriesLevel3.stream().map(category -> {
                return category.getId();
            }).collect(Collectors.toList());
            pageProducts = productRepository.findByCategory_IdIn(categoryIds, paging);
        } else {
            pageProducts = productRepository.findByCategoryId(request.getCategoryId(), paging);
        }

        products = pageProducts.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("products", productMapper.mapToProductDTOList(products));
        response.put("currentPage", pageProducts.getNumber());
        response.put("totalItems", pageProducts.getTotalElements());
        response.put("totalPages", pageProducts.getTotalPages());

        return response;
    }

    private Sort.Direction getSortDirection(String direction) {

        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals(("desc"))) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    public List<ProductDTO> getProductsBySpecialCategory(Long categoryId) {
        List<Long> categoryIdList = new ArrayList<>();
        CategoryDTO category = categoryService.findById(categoryId);
        List<CategoryDTO> categoriesLevel3 = new ArrayList<>();
        if (category.getLevel() == 1) {
            List<CategoryDTO> categoriesLevel2 = new ArrayList<>();
            categoriesLevel2 = categoryService.findByParentId(categoryId);
            if (categoriesLevel2.size() > 0) {
                for (int i = 0; i < categoriesLevel2.size(); i++) {
                    List<CategoryDTO> categories = categoryService.findByParentId(categoriesLevel2.get(i).getId());
                    if (categories.size() > 0) {
                        categoriesLevel3.addAll(categories);
                    }
                }
            }
        } else if (category.getLevel() == 2) {
            categoriesLevel3 = categoryService.findByParentId(categoryId);
        }

        if (categoriesLevel3.size() > 0) {
            categoryIdList = categoriesLevel3.stream().map(item -> {
                return item.getId();
            }).collect(Collectors.toList());

        } else {
            categoryIdList.add(categoryId);
        }
        List<Product> products = productRepository.findByCategoryIdInAndIsSpecial(categoryIdList, true);
        return productMapper.mapToProductDTOList(products);
    }

}
