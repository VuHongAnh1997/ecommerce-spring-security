package com.diepv.springsecuritydemo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.diepv.springsecuritydemo.dto.ProductDTO;
import com.diepv.springsecuritydemo.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO mapToProductDTO(Product product);

    Product mapToProduct(ProductDTO productDTO);

    List<ProductDTO> mapToProductDTOList(List<Product> productList);

    List<Product> mapToProductList(List<ProductDTO> productDTOs);
}
