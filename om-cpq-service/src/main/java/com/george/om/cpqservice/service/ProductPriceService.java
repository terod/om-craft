package com.george.om.cpqservice.service;

import com.george.om.cpqservice.model.ProductPrice;
import java.util.List;

public interface ProductPriceService {

    public ProductPrice getPriceByProductId(Long productId);

    public List<ProductPrice> fetchAllPrices();

    public ProductPrice savePrice(ProductPrice price);

    public void deletePrice(long productId);

}