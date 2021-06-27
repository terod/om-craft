package com.george.om.orderservice.controller;

import com.george.om.orderservice.clients.ServiceCallerFeignClient;
import com.george.om.orderservice.dto.*;
import com.george.om.orderservice.dto.mapper.OrderMapper;
import com.george.om.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/quote", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    @Autowired
    private ServiceCallerFeignClient serviceClient;

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductDetailsDto> getProductDetailsByProductId(@PathVariable("productId") long productId) {
        // Get Product details
        Optional<ProductDto> product = serviceClient
                .getProduct(productId);

        // Get Inventory details
        Optional<ProductInventoryDto> productInventory = serviceClient
                .getProductInventoryByProductId(productId);

        // Get Price details
        Optional<ProductPriceDto> productPrice = serviceClient
                .getProductPriceByProductId(productId);

        ProductDetailsDto productDetailsDto = ProductDetailsDto.newInstance(productId,
                product.isPresent() ? product.get().getName() : "Product id not found or Product Service down. Try again later",
                productInventory.isPresent() ? productInventory.get().getQuantity().toString() : "Product id not found in ProductInventory or ProductInventory Service down. Try again later",
                productPrice.isPresent() ? productPrice.get().getPrice().toString() : "Product id not found in Cpq or Cpq Service down. Try again later");

        return new ResponseEntity<>(productDetailsDto, HttpStatus.OK);

    }
}
