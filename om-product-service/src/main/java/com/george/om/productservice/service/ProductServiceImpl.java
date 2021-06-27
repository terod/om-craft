package com.george.om.productservice.service;

import com.george.om.productservice.exception.EntityType;
import com.george.om.productservice.exception.ExceptionType;
import com.george.om.productservice.exception.ProductServiceException;
import com.george.om.productservice.model.Product;
import com.george.om.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

import static com.george.om.productservice.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> fetchAllProducts(){

        List<Product> fetchedProducts = productRepository.findAll();

        if (CollectionUtils.isEmpty(fetchedProducts))
            exception(EntityType.PRODUCT, ENTITY_NOT_FOUND);

        return fetchedProducts;
    }

    @Override
    public Product getProductById(long productId){

        Optional<Product> fetchedProduct = productRepository.findById(productId);

        Product product = fetchedProduct.orElseThrow(() -> exception(EntityType.PRODUCT, ENTITY_NOT_FOUND, String.valueOf(productId)));

        return product;
    }

    @Override
    public Product saveProduct(Product product){

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public void deleteProductById(long productId){

        productRepository.deleteById(productId);
    }

    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ProductServiceException.throwException(entityType, exceptionType, args);
    }
}