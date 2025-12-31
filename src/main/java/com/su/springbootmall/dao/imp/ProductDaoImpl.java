package com.su.springbootmall.dao.imp;

import com.su.springbootmall.dao.ProductDao;
import com.su.springbootmall.dto.ProductRequest;
import com.su.springbootmall.dto.ProductQueryParams;
import com.su.springbootmall.model.Product;
import com.su.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT  product_id,product_name, category, image_url, price, stock, description," +
                " created_date, last_modified_date" +
                " FROM product WHERE 1=1"; // 1=1 是為了方便後續拼接 AND 條件

        Map<String, Object> map = new HashMap<>();

        if(productQueryParams.getCategory() != null){
            sql += " AND category = :category";
            map.put("category", productQueryParams.getCategory().name()); // Enum 轉 String
        }

        if(productQueryParams.getSearch() != null && !productQueryParams.getSearch().isEmpty()){
            sql += " AND product_name LIKE :search";
            map.put("search","%"+ productQueryParams.getSearch()+"%"); // SQL 的模糊查詢
        }

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT  product_id,product_name, category, image_url, price, stock, description," +
                " created_date, last_modified_date" +
                " FROM product" +
                " WHERE product_id = :productId;";

        Map<String,Object> map = new HashMap<>();

        map.put("productId",productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if(!productList.isEmpty()){
            return productList.get(0);
        }else{
            return null;
        }
    }


    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product (product_name,category,image_url,price,stock,description,created_date,last_modified_date)" +
                " VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate,:lastModifiedDate);";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("productName",productRequest.getProductName())  // 注意 Enum 轉 String
                .addValue("category",productRequest.getCategory().name())
                .addValue("imageUrl",productRequest.getImageUrl())
                .addValue("price",productRequest.getPrice())
                .addValue("stock",productRequest.getStock())
                .addValue("description",productRequest.getDescription());

        Date now = new Date();
        parameterSource.addValue("createdDate",now)
                        .addValue("lastModifiedDate",now);

        // KeyHolder 去拿回數據庫生成的主鍵。
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,parameterSource,keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql ="UPDATE product SET " +
                " product_name = :productName, " +
                " category = :category, " +
                " image_url = :imageUrl, " +
                " price = :price, " +
                " stock = :stock, " +
                " description = :description, " +
                " last_modified_date = :lastModifiedDate " +
                " WHERE product_id = :productId;";
        // 修改商品要記得加上 last_modified_date
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("productName",productRequest.getProductName())
                .addValue("category",productRequest.getCategory().name()) // 注意 Enum
                .addValue("imageUrl",productRequest.getImageUrl())
                .addValue("price",productRequest.getPrice())
                .addValue("stock",productRequest.getStock())
                .addValue("description",productRequest.getDescription())
                .addValue("lastModifiedDate",new Date())
                .addValue("productId",productId);

        namedParameterJdbcTemplate.update(sql,parameterSource);

    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId;";
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);
        namedParameterJdbcTemplate.update(sql,map);
    }
}
