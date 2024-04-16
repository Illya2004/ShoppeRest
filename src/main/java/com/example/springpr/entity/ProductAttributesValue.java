package com.example.springpr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributesValue {
    @Id
    @GeneratedValue(generator = "attributes_sequence")
    @SequenceGenerator(name="attributes_sequence",sequenceName="attributes_sequence", allocationSize=1)
    private Long id;

    @ManyToOne
    private Product product;

    private String name;
    private String value;

}
