package com.example.springpr.repository;

import com.example.springpr.entity.Product;
import com.example.springpr.entity.ProductAttributesValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductAttributesValueRepository extends JpaRepository<ProductAttributesValue, Long> {
    List<ProductAttributesValue> findAllByProduct_Id(Long id);
    void deleteAllByProduct_Id(Long id);
}
