package com.example.springpr.service.product;

import com.example.springpr.dto.product.*;
import com.example.springpr.entity.*;
import com.example.springpr.repository.product.ProductAttributesValueRepository;
import com.example.springpr.repository.product.ProductCategoriesRepository;
import com.example.springpr.repository.product.ProductImagesRepository;
import com.example.springpr.repository.product.ProductRepository;
import com.example.springpr.utils.ImageUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductAttributesValueRepository productAttributesValueRepository;
    private final ProductImagesRepository productImagesRepository;
    private final ProductCategoriesRepository productCategoriesRepository;


    public void create(ProductDTOGet productDTO){
        ProductCategories productCategories;
        String category = productDTO.getCategory();
        if(productCategoriesRepository.existsByCategory(category))
            productCategories = productCategoriesRepository.findByCategory(category);
        else{
            productCategories = ProductCategories.builder().category(category).build();
            productCategoriesRepository.save(productCategories);
        }

        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .short_description(productDTO.getShort_description())
                .full_description(productDTO.getFull_description())
                .sku(productDTO.getSku())
                .category(productCategories)
                .sub_category(productDTO.getSub_category()).build();

        ImageDTO imageDTO = productDTO.getImages().get(0);
        product.setImageURL(ImageUtil.base64ToImage(imageDTO.getBase64(),
                imageDTO.getExtension(),
                product.getId()));

        productRepository.save(product);

        productDTO.getImages().stream().map(image -> ProductImages.builder()
                .image(ImageUtil.base64ToImage(image.getBase64(),
                        image.getExtension(), product.getId()))
                .product(product)
                .build()).
                forEach(productImagesRepository::save);

        productDTO.getAttributes().stream().map(attribute -> ProductAttributesValue.builder().product(product)
                .name(attribute.getName())
                .value(attribute.getValue())
                .build()).forEach(productAttributesValueRepository::save);


    }

    public List<ProductMiniDTO> findAll(long limit, long offset,
                                        String filter,
                                        String category,
                                        float price_from,
                                        float price_to){

        List<Product> productList = productRepository.findAllByPriceBetween(price_from, price_to);


        if(category != null)
            productList = productList.stream().filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());

        if(filter != null && filter.equals("ASC"))
            productList = productList.stream().
                    sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());

        if(filter != null && filter.equals("DESC"))
            productList = productList.stream().
                    sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList());


        return productList.stream()
                .skip(offset)
                .limit(limit)
                .map(product -> new ProductMiniDTO(product.getId(), product.getName(),product.getImageURL(), product.getPrice()) )
                .collect(Collectors.toList());
    }

    public ProductDTOGive findById(Long id){
        Product product = productRepository.findById(id).get();

        List<ProductImages> productImages = productImagesRepository.findAllByProduct_Id(id);
        List<ProductAttributesValue> productAttributesValue = productAttributesValueRepository.findAllByProduct_Id(id);

        List<ProductAttributeDTO> attributeDTO = new ArrayList<>();
        productAttributesValue.forEach(attributesValue ->{
            String name = attributesValue.getName();
            String value = attributesValue.getValue();;

            attributeDTO.add(new ProductAttributeDTO(name, value));
        });

        List<String> imagesURL = new ArrayList<>();
        productImages.forEach(image ->{
            imagesURL.add(image.getImage());
        });

        return ProductDTOGive.builder()
                .name(product.getName())
                .price(product.getPrice())
                .short_description(product.getShort_description())
                .full_description(product.getFull_description())
                .images(imagesURL)
                .sku(product.getSku())
                .category(product.getCategory().getCategory())
                .sub_category(product.getSub_category())
                .attributes(attributeDTO).build();
    }
    public Product update(Product product){
        return productRepository.save(product);
    }
    @Transactional
    public void delete(Long id){
        productImagesRepository.deleteAllByProduct_Id(id);
        productAttributesValueRepository.deleteAllByProduct_Id(id);
        productRepository.deleteById(id);
    }

    public PriceDTO getMinAndMaxPriceCategory(String category){
        List<Product> productList = (List<Product>) productRepository.findAll();

        if(category != null)
            productList = productList.stream().
                    filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());

        return PriceDTO.builder()
                .max_price(productList.stream().max(Comparator.comparing(Product::getPrice)).get().getPrice())
                .min_price(productList.stream().min(Comparator.comparing(Product::getPrice)).get().getPrice())
                .build();
    }




}
