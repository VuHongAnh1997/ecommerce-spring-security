package com.diepv.springsecuritydemo.services;

import java.util.List;
import java.util.Optional;

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

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.mapToCategoryDTOList(categories);
    }

    public CategoryDTO findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            return categoryMapper.mapToCategoryDTO(category.get());
        } else
            return new CategoryDTO();
    }

    public List<CategoryDTO> findByParentId(Long id) {
        List<Category> categories = categoryRepository.findByParentId(id);
        return categoryMapper.mapToCategoryDTOList(categories);
    }

    public List<CategoryDTO> getSpecialCategories() {
        List<Category> categries = categoryRepository.findByIsSpecial(true);
        return categoryMapper.mapToCategoryDTOList(categries);
    }

}
