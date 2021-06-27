/*
 * Author: George Ebbinason
 */

package com.george.om.inventoryservice.controller;

import com.george.om.inventoryservice.dto.ProductInventoryDto;
import com.george.om.inventoryservice.dto.mapper.ProductInventoryMapper;
import com.george.om.inventoryservice.model.ProductInventory;
import com.george.om.inventoryservice.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/inventories", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProductInventoryController {
    @Autowired
    private ProductInventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<ProductInventoryDto>> fetchAllProductsInventory() {

        List<ProductInventory> fetchedProductsInventory = inventoryService.fetchAllProductsInventory();

        List<ProductInventoryDto> productInventoryResources = ProductInventoryMapper.map(fetchedProductsInventory);

        return new ResponseEntity<>(productInventoryResources, HttpStatus.OK);
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ProductInventoryDto> getProductInventoryByProductId(
            @PathVariable("productId") long productId) {

        ProductInventory fetchedProductInventory = inventoryService.getProductInventoryByProductId(productId);

        ProductInventoryDto productInventoryResource = ProductInventoryMapper.map(fetchedProductInventory);

        return new ResponseEntity<>(productInventoryResource, HttpStatus.OK);
    }

    @PostMapping(path = "/{productId}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ProductInventoryDto> saveProductInventory(@PathVariable("productId") long productId,
                                                                         @RequestBody ProductInventoryDto productInventoryResource) {

        // TODO : Validate product id by calling product catalog api

        ProductInventory productInventory = ProductInventoryMapper.map(productInventoryResource);
        productInventory.setProductId(productId);

        productInventory = inventoryService.saveProductInventory(productInventory);

        ProductInventoryDto savedProductInventoryResource = ProductInventoryMapper.map(productInventory);

        return new ResponseEntity<>(savedProductInventoryResource, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<ProductInventoryDto> deleteProductInventory(@PathVariable("productId") long productId) {

        inventoryService.deleteProductInventory(productId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
