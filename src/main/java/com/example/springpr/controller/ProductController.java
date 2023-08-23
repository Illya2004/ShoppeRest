package com.example.springpr.controller;

import com.example.springpr.dto.product.PriceDTO;
import com.example.springpr.dto.product.ProductDTOGet;
import com.example.springpr.dto.product.ProductDTOGive;
import com.example.springpr.dto.product.ProductMiniDTO;
import com.example.springpr.entity.Product;
import com.example.springpr.service.product.ProductService;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/product/")
@OpenAPIDefinition(info = @Info(title = "RestAPI ", description = "для вови"))
@Tag(name = "Product")
public class ProductController {


    private final ProductService productService;

    // !- PRODUCT

    @GetMapping
    @Operation(summary = "Get a list of products", description = "Retrieve a list of products available.",
            responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductMiniDTO.class))
            )})
    public ResponseEntity<List<ProductMiniDTO>> findAllProducts(@RequestParam("limit") long limit,
                                                        @RequestParam("offset") long offset,
                                                        @RequestParam(value = "filter", required = false) String filter,
                                                        @RequestParam(value = "category", required = false) String category,
                                                        @RequestParam("price_from") float price_from,
                                                        @RequestParam("price_to") float price_to){

        return new ResponseEntity<>(productService.findAll(limit, offset, filter,
                                                    category,price_from,price_to), HttpStatus.OK);
    }
    @GetMapping("find")
    @Operation(summary = "Get product by id", description = "Retrieve a list of products available.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTOGet.class))
                    )})
    public ResponseEntity<ProductDTOGive> findProductById(@RequestParam("id")  Long id){
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }
    @GetMapping("price")
    @Operation(summary = "Get min and max price", description = "Retrieve a list of products available.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceDTO.class))
                    )})
    public ResponseEntity<PriceDTO> getMinAndMaxPrice(@RequestParam(value = "category", required = false) String category){
        return new ResponseEntity<>(productService.getMinAndMaxPriceCategory(category), HttpStatus.OK);
    }


    @PostMapping
    @Operation(summary = "Create new product", description = "Create new product.")
    public HttpStatus createProduct(@RequestBody ProductDTOGet productDTO){
        productService.create(productDTO);
        return HttpStatus.OK;
    }

    @PutMapping
    @Operation(summary = "Update product", description = "Update product.")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @DeleteMapping
    @Operation(summary = "Delete product by id", description = "Delete product by id.")
    public HttpStatus deleteProductById(@RequestParam("id") Long id){
        productService.delete(id);
        return HttpStatus.OK;
    }


}
