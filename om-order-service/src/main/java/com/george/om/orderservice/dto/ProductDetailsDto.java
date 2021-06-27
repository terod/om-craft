package com.george.om.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDetailsDto {

    @JsonProperty("ProductId")
    private Long productId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("QuantityAvailable")
    private String quantity;

    @JsonProperty("Price")
    private String price;

    public static ProductDetailsDto newInstance(Long productId, String name, String quantity, String price) {
        ProductDetailsDto productResource = new ProductDetailsDto();
        productResource.setProductId(productId);
        productResource.setName(name);
        productResource.setQuantity(quantity);
        productResource.setPrice(price);
        return productResource;
    }
}
