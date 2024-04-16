package com.example.springpr.repository.product;

import com.example.springpr.entity.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {
    List<ProductImages> findAllByProduct_Id(Long id);
    void deleteAllByProduct_Id(Long id);
}
