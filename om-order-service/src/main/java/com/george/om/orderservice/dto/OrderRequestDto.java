/*
 * Author: George Ebbinason
 */

package com.george.om.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderRequestDto {

    @JsonProperty("UserId")
    private Long userId;

    @JsonProperty("ProductId")
    private Long productId;

    @JsonProperty("Quantity")
    private Integer quantity;

}