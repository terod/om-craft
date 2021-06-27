package com.george.om.cpqservice.controller;
import com.george.om.cpqservice.dto.ProductPriceDto;
import com.george.om.cpqservice.dto.mapper.ProductPriceMapper;
import com.george.om.cpqservice.model.ProductPrice;
import com.george.om.cpqservice.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/prices", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PriceController {
    @Autowired
    private ProductPriceService pricingService;

    @GetMapping
    public ResponseEntity<List<ProductPriceDto>> getPrices() {

        List<ProductPrice> fetchedProductsPrice = pricingService.fetchAllPrices();

        List<ProductPriceDto> pricesDto = ProductPriceMapper.map(fetchedProductsPrice);

        return new ResponseEntity<>(pricesDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductPriceDto> getProductPriceByProductId(@PathVariable("productId") Long productId) {

        ProductPrice fetchedPrice = pricingService.getPriceByProductId(productId);

        ProductPriceDto productPriceResource = ProductPriceMapper.map(fetchedPrice);

        return new ResponseEntity<>(productPriceResource, HttpStatus.OK);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ProductPriceDto> savePrice(@RequestBody ProductPriceDto priceRequest) {

        // TODO : Validate product id by calling product catalog api

        ProductPrice productPrice = ProductPriceMapper.map(priceRequest);

        productPrice = pricingService.savePrice(productPrice);

        ProductPriceDto savedProductPriceResource = ProductPriceMapper.map(productPrice);

        return new ResponseEntity<ProductPriceDto>(savedProductPriceResource, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<ProductPriceDto> deleteProductPrice(@PathVariable("productId") long productId) {

        pricingService.deletePrice(productId);

        return new ResponseEntity<ProductPriceDto>(HttpStatus.OK);
    }
}
