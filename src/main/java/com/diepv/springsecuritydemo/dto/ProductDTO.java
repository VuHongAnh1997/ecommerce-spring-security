package com.diepv.springsecuritydemo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    private String code;

    private String name;

    private CategoryDTO category;

    private Set<ColorDTO> colors;

    private Set<SizeDTO> sizes;

    private BigDecimal price;

    private BigDecimal discount;

    private String imageUrl;

    private String description;

    private Integer stockQuantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean isFeatured;

    private int salesQuantity;

    private boolean isSpecial;

}
