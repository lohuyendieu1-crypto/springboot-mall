package com.su.springbootmall.service;

import com.su.springbootmall.dto.ProductRequest;
import com.su.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

}
