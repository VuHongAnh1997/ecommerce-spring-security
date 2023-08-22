package com.diepv.springsecuritydemo.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    private Long id;

    private String code;

    @Column(name = "product_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private Set<Color> colors = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private Set<Size> sizes = new HashSet<>();

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

    @Column(name = "is_featured")
    private boolean isFeatured;

    @Column(name = "sales_quantity")
    private int salesQuantity;

    @Column(name = "is_special")
    private boolean isSpecial;

}
