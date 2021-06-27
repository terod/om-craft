package com.george.om.orderservice.controller;

import com.george.om.orderservice.clients.ServiceCallerFeignClient;
import com.george.om.orderservice.dto.*;
import com.george.om.orderservice.dto.mapper.OrderMapper;
import com.george.om.orderservice.model.Order;
import com.george.om.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ServiceCallerFeignClient serviceClient;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {

        List<Order> orders = orderService.fetchAllOrders();

        List<OrderDto> orderResources = OrderMapper.map(orders);

        return new ResponseEntity<>(orderResources, HttpStatus.OK);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") long orderId) {

        Order fetchedOrder = orderService.getOrderById(orderId);

        OrderDto orderResource = OrderMapper.map(fetchedOrder);

        return new ResponseEntity<>(orderResource, HttpStatus.OK);
    }




    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OrderDto> submitOrder(@RequestBody OrderRequestDto newOrderRequest) {

        // Get Product details
        Optional<ProductDto> product = serviceClient
                .getProduct(newOrderRequest.getProductId());

        // Get Inventory details
        Optional<ProductInventoryDto> productInventory = serviceClient
                .getProductInventoryByProductId(newOrderRequest.getProductId());

        // Get Price details
        Optional<ProductPriceDto> productPrice = serviceClient
                .getProductPriceByProductId(newOrderRequest.getProductId());

        // Try to process order
        Order savedOrder = orderService.processOrder(newOrderRequest, product, productInventory, productPrice);

        OrderDto orderResource = OrderMapper.map(savedOrder);

        return new ResponseEntity<>(orderResource, HttpStatus.OK);

    }

    @DeleteMapping(path = "/{orderId}")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable("orderId") long orderId) {

        orderService.deleteOrder(orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}