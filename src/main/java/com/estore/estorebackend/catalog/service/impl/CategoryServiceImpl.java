package com.estore.estorebackend.catalog.service.impl;

import com.estore.estorebackend.catalog.dto.CategoryDTO;
import com.estore.estorebackend.catalog.entity.Category;
import com.estore.estorebackend.catalog.mapper.CategoryMapper;
import com.estore.estorebackend.catalog.repository.CategoryRepository;
import com.estore.estorebackend.catalog.service.CategoryService;
import com.estore.estorebackend.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'id: " + id));
        return categoryMapper.toDTO(category);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = categoryMapper.toEntity(dto);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'id: " + id));
        Category category = categoryMapper.toEntity(dto);
        category.setId(id);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'id: " + id));
        categoryRepository.deleteById(id);
    }
}