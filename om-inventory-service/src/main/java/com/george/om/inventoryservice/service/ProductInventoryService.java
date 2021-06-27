package com.george.om.inventoryservice.service;

import com.george.om.inventoryservice.model.ProductInventory;

import java.util.List;

public interface ProductInventoryService {

    public List<ProductInventory> fetchAllProductsInventory();

    public ProductInventory getProductInventoryByProductId(long productId);

    public ProductInventory saveProductInventory(ProductInventory productInventory);

    public void deleteProductInventory(long productId);

}