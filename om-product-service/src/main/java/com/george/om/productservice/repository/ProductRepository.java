/*
 * Author: George Ebbinason
 */

package com.george.om.productservice.repository;

import com.george.om.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}