package com.su.springbootmall.service;

import com.su.springbootmall.constant.ProductCategory;
import com.su.springbootmall.dto.ProductRequest;
import com.su.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category,String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
