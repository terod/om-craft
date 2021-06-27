/*
 * Author: George Ebbinason
 */

package com.george.om.inventoryservice.dto.mapper;

import com.george.om.inventoryservice.dto.ProductInventoryDto;
import com.george.om.inventoryservice.model.ProductInventory;

import java.util.List;
import java.util.stream.Collectors;

public class ProductInventoryMapper {

    public static ProductInventoryDto map(ProductInventory productInventory) {
        ProductInventoryDto productInventoryResource = ProductInventoryDto
                .newInstance(productInventory.getProductId(), productInventory.getQuantity());
        return productInventoryResource;
    }

    public static List<ProductInventoryDto> map(List<ProductInventory> productInventories) {
        List<ProductInventoryDto> productInventoryResources = productInventories.stream()
                .map(productInventory -> ProductInventoryDto.newInstance(productInventory.getProductId(),
                        productInventory.getQuantity()))
                .collect(Collectors.toList());
        return productInventoryResources;
    }

    public static ProductInventory map(ProductInventoryDto productInventoryResource) {
        ProductInventory productInventory = ProductInventory.newInstance(productInventoryResource.getProductId(),
                productInventoryResource.getQuantity());
        return productInventory;
    }
}
