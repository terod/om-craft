/*
 * Author: George Ebbinason
 */

package com.george.om.orderservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

/**
 * @author George Ebbinason
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "OM_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_QUANTITY")
    private Integer quantity;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @Column(name = "CREATION_DATE")
    private LocalDateTime dateCreated;

    @Column(name = "USER_ID")
    private Long userId;

    public static Order newInstance(long userId, long productId, int quantity, BigDecimal totalPrice) {
        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        return order;
    }

}