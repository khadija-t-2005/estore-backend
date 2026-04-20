package com.estore.estorebackend.catalog.mapper;

import com.estore.estorebackend.catalog.dto.CategoryDTO;
import com.estore.estorebackend.catalog.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO dto);
}