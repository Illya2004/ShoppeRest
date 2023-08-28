package com.example.springpr.repository.product;


import com.example.springpr.entity.Product;

import com.example.springpr.entity.ProductElasticsearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetween(double priceFrom, double priceTo);

}
