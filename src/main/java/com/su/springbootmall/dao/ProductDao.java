package com.su.springbootmall.dao;

import com.su.springbootmall.dto.ProductRequest;
import com.su.springbootmall.dto.ProductQueryParams;
import com.su.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
