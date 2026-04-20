package com.estore.estorebackend.catalog.repository;

import com.estore.estorebackend.catalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:categoryId IS NULL OR p.category.id = :categoryId)")
    List<Product> findByFilters(@Param("name") String name,
                                @Param("categoryId") Long categoryId);
}