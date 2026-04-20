package com.estore.estorebackend.catalog.mapper;

import com.estore.estorebackend.catalog.dto.ProductDTO;
import com.estore.estorebackend.catalog.dto.ProductDetailDTO;
import com.estore.estorebackend.catalog.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductDTO toDTO(Product product);

    @Mapping(source = "category", target = "category")
    ProductDetailDTO toDetailDTO(Product product);

    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductDTO dto);
}