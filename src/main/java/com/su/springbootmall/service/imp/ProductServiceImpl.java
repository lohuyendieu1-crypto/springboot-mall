package com.su.springbootmall.service.imp;

import com.su.springbootmall.dao.ProductDao;
import com.su.springbootmall.dto.ProductRequest;
import com.su.springbootmall.model.Product;
import com.su.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
       return productDao.createProduct(productRequest);
    }
}
