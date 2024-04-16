package com.example.springpr.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMiniDTO {
    private Long id;
    private String name;
    private String image;
    private float price;
}
