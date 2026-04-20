package com.estore.estorebackend.catalog.service;

import com.estore.estorebackend.catalog.dto.ProductDTO;
import com.estore.estorebackend.catalog.dto.ProductDetailDTO;
import com.estore.estorebackend.catalog.dto.ProductFilterDTO;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDetailDTO getProductById(Long id);
    List<ProductDTO> searchProducts(ProductFilterDTO filter);
    ProductDTO createProduct(ProductDTO dto);
    ProductDTO updateProduct(Long id, ProductDTO dto);
    void deleteProduct(Long id);
}