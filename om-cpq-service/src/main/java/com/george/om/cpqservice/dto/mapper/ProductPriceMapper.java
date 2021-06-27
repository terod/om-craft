/*
 * Author: George Ebbinason
 */

package com.george.om.cpqservice.dto.mapper;

import com.george.om.cpqservice.dto.ProductPriceDto;
import com.george.om.cpqservice.model.ProductPrice;

import java.util.List;
import java.util.stream.Collectors;

public class ProductPriceMapper {

    public static ProductPriceDto map(ProductPrice price) {
        ProductPriceDto priceResource = ProductPriceDto.newInstance(price.getProductId(), price.getPrice());
        return priceResource;
    }

    public static List<ProductPriceDto> map(List<ProductPrice> prices) {
        List<ProductPriceDto> pricesResource = prices.stream()
                .map(price -> ProductPriceDto.newInstance(price.getProductId(), price.getPrice()))
                .collect(Collectors.toList());
        return pricesResource;
    }

    public static ProductPrice map(ProductPriceDto productPriceDto) {
        ProductPrice productPrice = ProductPrice.newInstance(productPriceDto.getProductId(),
                productPriceDto.getPrice());
        return productPrice;
    }
}
