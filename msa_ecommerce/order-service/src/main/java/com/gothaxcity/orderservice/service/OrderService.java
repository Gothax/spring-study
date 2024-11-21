package com.gothaxcity.orderservice.service;


import com.gothaxcity.orderservice.dto.OrderDto;
import com.gothaxcity.orderservice.entity.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
