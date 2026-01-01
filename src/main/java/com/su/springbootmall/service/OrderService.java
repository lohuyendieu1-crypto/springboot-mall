package com.su.springbootmall.service;

import com.su.springbootmall.dto.CreateOrderRequest;
import com.su.springbootmall.dto.OrderQueryParams;
import com.su.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
