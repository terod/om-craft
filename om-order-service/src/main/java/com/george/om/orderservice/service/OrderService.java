/*
 * Author: George Ebbinason
 */

package com.george.om.orderservice.service;

import com.george.om.orderservice.dto.OrderRequestDto;
import com.george.om.orderservice.dto.ProductDto;
import com.george.om.orderservice.dto.ProductInventoryDto;
import com.george.om.orderservice.dto.ProductPriceDto;
import com.george.om.orderservice.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public List<Order> fetchAllOrders();

    public Order getOrderById(long orderId);

    public void deleteOrder(long orderId);

    public Order processOrder(OrderRequestDto newOrderRequest, Optional<ProductDto> product,
                              Optional<ProductInventoryDto> productInventory, Optional<ProductPriceDto> productPrice);

}