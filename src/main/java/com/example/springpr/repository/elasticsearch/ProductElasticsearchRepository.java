package com.example.springpr.repository.elasticsearch;

import com.example.springpr.entity.ProductElasticsearch;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductElasticsearchRepository extends ElasticsearchRepository<ProductElasticsearch, Long> {

}
