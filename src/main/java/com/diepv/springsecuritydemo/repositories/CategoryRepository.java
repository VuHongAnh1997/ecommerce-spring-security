package com.diepv.springsecuritydemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diepv.springsecuritydemo.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParentIdIsNull();

    List<Category> findByParentId(Long parentId);

    List<Category> findByIsSpecial(boolean special);
}
