package com.diepv.springsecuritydemo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.diepv.springsecuritydemo.dto.CategoryDTO;
import com.diepv.springsecuritydemo.models.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO

            mapToCategoryDTO(Category category);

    Category mapToCategory(CategoryDTO categoryDTO);

    List<Category> mapToCategoryList(List<CategoryDTO> categoryList);

    List<CategoryDTO> mapToCategoryDTOList(List<Category> categories);
}
