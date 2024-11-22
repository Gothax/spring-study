package com.gothaxcity.orderservice.controller;

import com.gothaxcity.orderservice.dto.OrderDto;
import com.gothaxcity.orderservice.entity.OrderEntity;
import com.gothaxcity.orderservice.service.OrderService;
import com.gothaxcity.orderservice.vo.RequestOrder;
import com.gothaxcity.orderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.*;
import static org.modelmapper.convention.MatchingStrategies.STRICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor(access = PROTECTED)
public class OrderController {

    private final Environment env;
    private final OrderService orderService;

    @GetMapping("/health_check")
    public String health_check() {
        return String.format("It's Working in Order Service on PORT %s",
                             env.getProperty("local.server.port"));
    }


    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
                                                     @RequestBody RequestOrder orderDetails) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);

        OrderDto orderDto = mapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);
        OrderDto createdOrder = orderService.createOrder(orderDto);

        ResponseOrder responseOrder = mapper.map(createdOrder, ResponseOrder.class);
        return ResponseEntity.status(CREATED).body(responseOrder);
    }


    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable("userId") String userId) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);

        Iterable<OrderEntity> ordersByUserId = orderService.getOrdersByUserId(userId);
        List<ResponseOrder> result = new ArrayList<>();

        ordersByUserId.forEach(orderEntity -> {
            result.add(mapper.map(orderEntity, ResponseOrder.class));
        });

        return ResponseEntity.status(OK).body(result);
    }

}
