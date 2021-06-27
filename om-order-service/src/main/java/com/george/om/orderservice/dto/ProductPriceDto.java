/*
 * Author: George Ebbinason
 */

package com.george.om.orderservice.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductPriceDto {

    @JsonProperty("ProductId")
    private Long productId;

    @JsonProperty("Price")
    private BigDecimal price;

    public static ProductPriceDto newInstance(Long productId, BigDecimal price) {
        ProductPriceDto pp = new ProductPriceDto();
        pp.setProductId(productId);
        pp.setPrice(price);
        return pp;
    }

}