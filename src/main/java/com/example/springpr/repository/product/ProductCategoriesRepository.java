package com.example.springpr.repository.product;

import com.example.springpr.entity.ProductCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoriesRepository extends JpaRepository<ProductCategories, Long> {
    ProductCategories findByCategory(String category);
    default boolean existsByCategory(String category) {
        return findByCategory(category) != null;
    }
}
