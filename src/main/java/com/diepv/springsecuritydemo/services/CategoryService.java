package com.diepv.springsecuritydemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diepv.springsecuritydemo.dto.CategoryDTO;
import com.diepv.springsecuritydemo.mapper.CategoryMapper;
import com.diepv.springsecuritydemo.models.Category;
import com.diepv.springsecuritydemo.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public void addCategoryList(List<CategoryDTO> categoryList) {

        List<Category> categories = categoryMapper.mapToCategoryList(categoryList);
        categoryRepository.saveAll(categories);
    }

    public List<CategoryDTO> getRootCategories() {
        List<Category> categories = categoryRepository.findByParentIdIsNull();
        return categoryMapper.mapToCategoryDTOList(categories);
    }

}
