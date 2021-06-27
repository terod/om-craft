/*
 * Author: George Ebbinason
 */

package com.george.om.orderservice.repository;

import com.george.om.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}