/*
 * Author: George Ebbinason
 */

package com.george.om.orderservice.dto.mapper;
import com.george.om.orderservice.dto.OrderDto;
import com.george.om.orderservice.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto map(Order order) {
        OrderDto orderResource = OrderDto.newInstance(order.getUserId(), order.getOrderId(),
                order.getProductId(), order.getQuantity(), order.getTotalPrice(), order.getDateCreated());
        return orderResource;
    }

    public static List<OrderDto> map(List<Order> orders) {
        List<OrderDto> orderResources = orders.stream()
                .map(order -> OrderDto.newInstance(order.getUserId(), order.getOrderId(), order.getProductId(),
                        order.getQuantity(), order.getTotalPrice(), order.getDateCreated()))
                .collect(Collectors.toList());
        return orderResources;
    }

    public static Order map(OrderDto orderResource) {
        Order order = Order.newInstance(orderResource.getUserId(), orderResource.getProductId(),
                orderResource.getQuantity(), orderResource.getTotalPrice());
        return order;
    }
}
