/*
 * Author: George Ebbinason
 */

package com.george.om.orderservice.service;

import com.george.om.orderservice.dto.OrderRequestDto;
import com.george.om.orderservice.dto.ProductDto;
import com.george.om.orderservice.dto.ProductInventoryDto;
import com.george.om.orderservice.dto.ProductPriceDto;
import com.george.om.orderservice.exception.EntityType;
import com.george.om.orderservice.exception.ExceptionType;
import com.george.om.orderservice.exception.OrderServiceException;
import com.george.om.orderservice.model.Order;
import com.george.om.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.george.om.orderservice.exception.ExceptionType.ENTITY_EXCEPTION;
import static com.george.om.orderservice.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> fetchAllOrders(){

        List<Order> fetchedOrders = orderRepository.findAll();

        if (CollectionUtils.isEmpty(fetchedOrders))
            exception(EntityType.ORDER, ENTITY_NOT_FOUND);

        return fetchedOrders;
    }

    @Override
    public Order getOrderById(long orderId){

        Optional<Order> fetchedOrder = orderRepository.findById(orderId);

        Order order = fetchedOrder.orElseThrow(() -> exception(EntityType.ORDER, ENTITY_NOT_FOUND, String.valueOf(orderId)));

        return order;
    }

    @Override
    public void deleteOrder(long orderId){
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order processOrder(OrderRequestDto newOrderRequest, Optional<ProductDto> product,
                              Optional<ProductInventoryDto> productInventory, Optional<ProductPriceDto> productPrice)
            {

        ProductDto productResource = product
                .orElseThrow(() -> exception(EntityType.PRODUCT, ENTITY_NOT_FOUND));

        ProductInventoryDto productInventoryResource = productInventory
                .orElseThrow(() -> exception(EntityType.PRODUCTINVENTORY, ENTITY_NOT_FOUND));

        ProductPriceDto productPriceResource = productPrice.orElseThrow(
                () -> exception(EntityType.PRODUCTPRICE, ENTITY_NOT_FOUND));

        // Validate quantity
        if (newOrderRequest.getQuantity().intValue() > productInventoryResource.getQuantity().intValue())
            exception(EntityType.ORDER, ENTITY_EXCEPTION, "Requested product quantity not available");

        // Calculate total price
        BigDecimal unitProductPrice = productPriceResource.getPrice();
        BigDecimal totalPrice = unitProductPrice.multiply(new BigDecimal(newOrderRequest.getQuantity().intValue()));

        Order order = Order.newInstance(newOrderRequest.getUserId(), productResource.getProductId(),
                newOrderRequest.getQuantity(), totalPrice);

        // Save order
        order = orderRepository.save(order);

        return order;
    }

    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return OrderServiceException.throwException(entityType, exceptionType, args);
    }


}