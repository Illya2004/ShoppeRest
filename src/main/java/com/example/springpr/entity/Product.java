package com.example.springpr.entity;


import jakarta.persistence.*;
import lombok.*;




@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(generator = "product_sequence")
    @SequenceGenerator(name="product_sequence", sequenceName="product_sequence", allocationSize=1)
    private Long id;

    private String name;

    private Float price;

    private String short_description;
    private String full_description;
    private String sku;

    @ManyToOne
    private ProductCategories category;

    private String sub_category;
    private String imageURL;

}
