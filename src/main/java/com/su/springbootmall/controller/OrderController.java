package com.su.springbootmall.controller;

import com.su.springbootmall.dto.CreatOrderRequest;
import com.su.springbootmall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreatOrderRequest creatOrderRequest) {

        Integer orderId = orderService.createOrder(userId, creatOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }
}
