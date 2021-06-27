package com.george.om.productservice.dto.mapper;

import com.george.om.productservice.dto.ProductDto;
import com.george.om.productservice.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDto map(Product product) {
        ProductDto productResource = ProductDto.newInstance(product.getProductId(), product.getName(),
                product.getDesc(), product.getDateCreated(), product.getDateUpdated());
        return productResource;
    }

    public static List<ProductDto> map(List<Product> products) {
        List<ProductDto> productResources = products.stream()
                .map(product -> ProductDto.newInstance(product.getProductId(), product.getName(),
                        product.getDesc(), product.getDateCreated(), product.getDateUpdated()))
                .collect(Collectors.toList());
        return productResources;
    }

    public static Product map(ProductDto productDto) {
        Product product = Product.newInstance(productDto.getProductId(), productDto.getName(),
                productDto.getDesc());
        return product;
    }
}
