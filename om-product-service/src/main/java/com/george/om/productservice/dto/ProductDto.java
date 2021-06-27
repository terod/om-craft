package com.george.om.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ProductDto{

    @JsonProperty("ProductId")
    private Long productId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String desc;

    public static ProductDto newInstance(Long productId, String name, String desc, LocalDateTime dateCreated,
                                              LocalDateTime dateUpdated) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productId);
        productDto.setName(name);
        productDto.setDesc(desc);
        return productDto;
    }
}