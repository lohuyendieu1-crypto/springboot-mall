package com.su.springbootmall.service.imp;

import com.su.springbootmall.dao.OrderDao;
import com.su.springbootmall.dao.ProductDao;
import com.su.springbootmall.dto.BuyItem;
import com.su.springbootmall.dto.CreatOrderRequest;
import com.su.springbootmall.model.OrderItem;
import com.su.springbootmall.model.Product;
import com.su.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional // 只要修改到多張表，就要加上事務
    @Override
    public Integer createOrder(Integer userId, CreatOrderRequest creatOrderRequest) {

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();


        for (BuyItem buyItem : creatOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            // 計算總價
            int amouont = buyItem.getQuantity() * product.getPrice();
            totalAmount += amouont;

            // 轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amouont);

            orderItemList.add(orderItem);
        }



        // 創建訂單
        Integer orderId = orderDao.createOrder(userId,totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
