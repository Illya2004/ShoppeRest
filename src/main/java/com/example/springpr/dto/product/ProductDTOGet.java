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
public class ProductDTOGet {

    private String name;
    private float price;
    private String short_description;
    private String full_description;
    private String sku;
    private String category;
    private String sub_category;
    private List<ImageDTO> images;
    private List<ProductAttributeDTO> attributes;

}
