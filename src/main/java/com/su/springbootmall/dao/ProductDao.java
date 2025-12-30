package com.su.springbootmall.dao;

import com.su.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
