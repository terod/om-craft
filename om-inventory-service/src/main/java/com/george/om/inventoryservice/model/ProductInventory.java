package com.george.om.inventoryservice.model;

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
@Table(name = "OM_PRODUCT_INVENTORY")
public class ProductInventory {
    @Id
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "QUANTITY", updatable = true, nullable = false)
    private Integer quantity;

    @Column(name = "CREATION_DATE", updatable = false, nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "LAST_UPDATED_DATE", updatable = true, nullable = false)
    private LocalDateTime dateUpdated;

    public static ProductInventory newInstance(Long productId, Integer quantity) {
        ProductInventory pi = new ProductInventory();
        pi.setProductId(productId);
        pi.setQuantity(quantity);
        pi.setDateCreated(LocalDateTime.now(Clock.systemUTC()));
        pi.setDateUpdated(LocalDateTime.now(Clock.systemUTC()));
        return pi;
    }

}