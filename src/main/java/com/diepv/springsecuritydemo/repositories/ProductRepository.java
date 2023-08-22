package com.diepv.springsecuritydemo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diepv.springsecuritydemo.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByIsFeatured(boolean featured);

    public Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Product> findByCategory_IdIn(List<Long> categoryIds, Pageable pageable);

    List<Product> findByCategoryIdInAndIsSpecial(List<Long> categoryId, boolean isSpecial);

}
