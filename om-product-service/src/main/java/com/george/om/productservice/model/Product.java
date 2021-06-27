/*
 * Author: George Ebbinason
 */

package com.george.om.productservice.model;

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
@Table(name = "OM_PRODUCT")
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "NAME", length = 50, updatable = true, nullable = false)
    private String name;

    @Column(name = "DESC", length = 1000, updatable = true, nullable = false)
    private String desc;

    @Column(name = "CREATION_DATE", updatable = false, nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "LAST_UPDATED_DATE", updatable = true, nullable = false)
    private LocalDateTime dateUpdated;

    public static Product newInstance(Long productId, String name, String desc) {
        Product p = new Product();
        p.setProductId(productId);
        p.setName(name);
        p.setDesc(desc);
        p.setDateCreated(LocalDateTime.now(Clock.systemUTC()));
        p.setDateUpdated(LocalDateTime.now(Clock.systemUTC()));
        return p;
    }

}