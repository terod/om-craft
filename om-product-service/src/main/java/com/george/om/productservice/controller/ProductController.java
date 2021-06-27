package com.george.om.productservice.controller;

import com.george.om.productservice.dto.ProductDto;
import com.george.om.productservice.dto.mapper.ProductMapper;
import com.george.om.productservice.model.Product;
import com.george.om.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> fetchAllProducts() {

        List<Product> fetchedProducts = productService.fetchAllProducts();

        List<ProductDto> productResources = ProductMapper.map(fetchedProducts);

        return new ResponseEntity<>(productResources, HttpStatus.OK);
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ProductDto> fetchProductById(@PathVariable("productId") long productId) {

        Product fetchedProduct = productService.getProductById(productId);

        ProductDto productResource = ProductMapper.map(fetchedProduct);

        return new ResponseEntity<>(productResource, HttpStatus.OK);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productResource) {

        Product product = ProductMapper.map(productResource);

        product = productService.saveProduct(product);

        ProductDto savedProductResource = ProductMapper.map(product);

        return new ResponseEntity<>(savedProductResource, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("productId") long productId) {

        productService.deleteProductById(productId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
