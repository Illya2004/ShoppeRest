package com.example.springpr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategories {
    @Id
    @GeneratedValue(generator = "product_categories_sequence")
    @SequenceGenerator(name="product_categories_sequence", sequenceName="product_categories_sequence", allocationSize=1)
    private Long id;
    private String category;
}
