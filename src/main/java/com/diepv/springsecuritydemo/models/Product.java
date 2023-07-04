package com.diepv.springsecuritydemo.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "product_size")
    private String size;

    @Column(name = "product_price")
    private BigDecimal price;

    @Column(name = "product_discount")
    private BigDecimal discount;

    @Column(name = "product_image_url")
    private String imageUrl;

    @Column(name = "product_decription")
    private String description;

    @Column(name = "product_stock_quantity")
    private Integer stockQuantity;

    @Column(name = "product_created_at")
    private LocalDateTime createdAt;

    @Column(name = "product_updated_at")
    private LocalDateTime updatedAt;

    private boolean isFeatured;

}
