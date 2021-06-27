/*
 * Author: George Ebbinason
 */

package com.george.om.orderservice.clients;

import java.util.Optional;

import com.george.om.orderservice.dto.ProductDto;
import com.george.om.orderservice.dto.ProductInventoryDto;
import com.george.om.orderservice.dto.ProductPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;


@Component
public class ServiceCallerFeignClient {

    @Autowired
    private ProductServiceClient productCatalogServiceClient;

    @Autowired
    private InventoryServiceClient inventoryServiceClient;

    @Autowired
    private PriceServiceClient pricingServiceClient;

    @Autowired
    private ProductServiceFallback productServiceFallback;

    @Autowired
    private InventoryServiceFallback inventoryServiceFallback;

    @Autowired
    private PriceServiceFallback priceServiceFallback;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    /**
     * @param productId
     * @return
     */
    public Optional<ProductPriceDto> getProductPriceByProductId(long productId) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("pricecircuitbreaker");
        ProductPriceDto productPrice = circuitBreaker.run(() -> pricingServiceClient.getPriceByProductId(productId), throwable -> priceServiceFallback.getPriceByProductId(productId));
        return Optional.ofNullable(productPrice);
    }


    /**
     * @param productId
     * @return
     */
    public Optional<ProductDto> getProduct(long productId) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("productcircuitbreaker");
        ProductDto productResource = circuitBreaker.run(() -> productCatalogServiceClient.getProductById(productId), throwable -> productServiceFallback.getProductById(productId));
        return Optional.ofNullable(productResource);
    }

    /**
     * @param productId
     * @return
     */
    public Optional<ProductInventoryDto> getProductInventoryByProductId(long productId) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("inventorycircuitbreaker");
        ProductInventoryDto productInventoryResource = circuitBreaker.run(() -> inventoryServiceClient.getInventoryByProductId(productId), throwable -> inventoryServiceFallback.getInventoryByProductId(productId));
        return Optional.ofNullable(productInventoryResource);
    }

}