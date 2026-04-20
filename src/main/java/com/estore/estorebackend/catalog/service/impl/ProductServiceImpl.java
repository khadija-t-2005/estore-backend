package com.estore.estorebackend.catalog.service.impl;

import com.estore.estorebackend.catalog.dto.ProductDTO;
import com.estore.estorebackend.catalog.dto.ProductDetailDTO;
import com.estore.estorebackend.catalog.dto.ProductFilterDTO;
import com.estore.estorebackend.catalog.entity.Product;
import com.estore.estorebackend.catalog.mapper.ProductMapper;
import com.estore.estorebackend.catalog.repository.ProductRepository;
import com.estore.estorebackend.catalog.service.ProductService;
import com.estore.estorebackend.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDetailDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'id: " + id));
        return productMapper.toDetailDTO(product);
    }

    @Override
    public List<ProductDTO> searchProducts(ProductFilterDTO filter) {
        return productRepository.findByFilters(filter.getName(), filter.getCategoryId())
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = productMapper.toEntity(dto);
        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'id: " + id));
        Product product = productMapper.toEntity(dto);
        product.setId(id);
        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'id: " + id));
        productRepository.deleteById(id);
    }
}