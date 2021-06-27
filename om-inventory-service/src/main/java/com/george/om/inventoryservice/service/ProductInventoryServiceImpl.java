package com.george.om.inventoryservice.service;

import com.george.om.inventoryservice.exception.EntityType;
import com.george.om.inventoryservice.exception.ExceptionType;
import com.george.om.inventoryservice.exception.InventoryServiceException;
import com.george.om.inventoryservice.model.ProductInventory;
import com.george.om.inventoryservice.repository.ProductInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

import static com.george.om.inventoryservice.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    private ProductInventoryRepository inventoryRepository;

    @Override
    public List<ProductInventory> fetchAllProductsInventory() {

        List<ProductInventory> fetchedProductsInventory = inventoryRepository.findAll();

        if (CollectionUtils.isEmpty(fetchedProductsInventory))
            exception(EntityType.PRODUCTINVENTORY, ENTITY_NOT_FOUND);

        return fetchedProductsInventory;
    }

    @Override
    public ProductInventory getProductInventoryByProductId(long productId) {

        Optional<ProductInventory> fetchProductInventory = inventoryRepository.findById(productId);

        ProductInventory productInventory = fetchProductInventory
                .orElseThrow(() -> exception(EntityType.PRODUCTINVENTORY, ENTITY_NOT_FOUND, String.valueOf(productId)));

        return productInventory;
    }

    @Override
    public ProductInventory saveProductInventory(ProductInventory productInventory) {
        return inventoryRepository.save(productInventory);
    }

    @Override
    public void deleteProductInventory(long productId) {
        inventoryRepository.deleteById(productId);
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
        return InventoryServiceException.throwException(entityType, exceptionType, args);
    }


}