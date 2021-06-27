package com.george.om.cpqservice.service;

import com.george.om.cpqservice.exception.EntityType;
import com.george.om.cpqservice.exception.ExceptionType;
import com.george.om.cpqservice.exception.CpqServiceException;
import com.george.om.cpqservice.model.ProductPrice;
import com.george.om.cpqservice.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

import static com.george.om.cpqservice.exception.ExceptionType.ENTITY_NOT_FOUND;


@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Override
    public ProductPrice getPriceByProductId(Long productId) {

        Optional<ProductPrice> fetchedPrice = Optional.ofNullable(productPriceRepository.findByProductId(productId));

        ProductPrice price = fetchedPrice
                .orElseThrow(() -> exception(EntityType.PRICE, ENTITY_NOT_FOUND, String.valueOf(productId)));

        return price;
    }

    @Override
    public List<ProductPrice> fetchAllPrices() {

        List<ProductPrice> fetchedProductsPrice = productPriceRepository.findAll();

        if (CollectionUtils.isEmpty(fetchedProductsPrice)) {
            exception(EntityType.PRICE, ENTITY_NOT_FOUND);
        }

        return fetchedProductsPrice;
    }

    @Override
    public ProductPrice savePrice(ProductPrice price) {
        return productPriceRepository.save(price);
    }

    @Override
    public void deletePrice(long productId) {
        productPriceRepository.deleteByProductId(productId);
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
        return CpqServiceException.throwException(entityType, exceptionType, args);
    }



}