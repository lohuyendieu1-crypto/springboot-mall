package com.su.springbootmall.service;

import com.su.springbootmall.dto.CreatOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreatOrderRequest creatOrderRequest);
}
