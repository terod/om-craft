package com.george.om.cpqservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name = "OM_PRODUCT_PRICE")
public class ProductPrice{

    @Id
    @Column(name = "PRODUCT_ID", updatable = false)
    private Long productId;

    @Column(name = "PRICE", updatable = true, nullable = false)
    private BigDecimal price;

    @Column(name = "CREATION_DATE", updatable = false, nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "LAST_UPDATED_DATE", updatable = true, nullable = false)
    private LocalDateTime dateUpdated;

    public static ProductPrice newInstance(Long productId, BigDecimal price) {
        ProductPrice pp = new ProductPrice();
        pp.setProductId(productId);
        pp.setPrice(price);
        pp.setDateCreated(LocalDateTime.now(Clock.systemUTC()));
        pp.setDateUpdated(LocalDateTime.now(Clock.systemUTC()));
        return pp;
    }

}