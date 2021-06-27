package com.george.om.productservice.service;

import com.george.om.productservice.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> fetchAllProducts();

    public Product getProductById(long productId);

    public Product saveProduct(Product product);

    public void deleteProductById(long productId);

}
