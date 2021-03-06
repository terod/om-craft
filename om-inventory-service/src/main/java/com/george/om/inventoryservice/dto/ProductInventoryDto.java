/*
 * Author: George Ebbinason
 */

package com.george.om.inventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductInventoryDto{

    @JsonProperty("ProductId")
    private Long productId;

    @JsonProperty("Quantity")
    private Integer quantity;

    public static ProductInventoryDto newInstance(Long productId, Integer quantity) {
        ProductInventoryDto pi = new ProductInventoryDto();
        pi.setProductId(productId);
        pi.setQuantity(quantity);
        return pi;
    }

}