package com.george.om.orderservice.dto;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {

    @JsonProperty("ProductId")
    private Long productId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String desc;


    public static ProductDto newInstance(Long productId, String name, String desc) {
        ProductDto productResource = new ProductDto();
        productResource.setProductId(productId);
        productResource.setName(name);
        productResource.setDesc(desc);
        return productResource;
    }

}