/*
 * Author: George Ebbinason
 */

package com.george.om.cpqservice.repository;

import com.george.om.cpqservice.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {
    ProductPrice findByProductId(Long productId);
    void deleteByProductId(Long deleteById);
}