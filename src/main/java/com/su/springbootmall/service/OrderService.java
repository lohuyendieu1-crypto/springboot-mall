package com.su.springbootmall.service;

import com.su.springbootmall.dto.CreateOrderRequest;
import com.su.springbootmall.model.Order;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
