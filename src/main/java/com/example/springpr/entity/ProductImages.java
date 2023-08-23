package com.example.springpr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImages {
    @Id
    @GeneratedValue(generator = "images_sequence")
    @SequenceGenerator(name="images_sequence", sequenceName="images_sequence", allocationSize=1)
    private Long id;
    private String image;

    @ManyToOne
    private Product product;
}
