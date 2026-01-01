package com.su.springbootmall.dao.imp;

import com.su.springbootmall.dao.OrderDao;
import com.su.springbootmall.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
       String sql = "INSERT INTO `order` (user_id,total_amount,created_date,last_modified_date) " +
               "VALUES(:userId, :totalAmount, :createdDate, :lastModifiedDate)";

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("userId", userId)
                          .addValue("totalAmount", totalAmount)
                          .addValue("createdDate", new Date())
                          .addValue("lastModifiedDate", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,sqlParameterSource,keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        String sql = "INSERT INTO order_item (order_id, product_id, quantity, amount) " +
                "VALUES (:orderId, :productId, :quantity, :amount)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];

        for(int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("orderId", orderId)
                                 .addValue("productId", orderItem.getProductId())
                                 .addValue("quantity", orderItem.getQuantity())
                                 .addValue("amount", orderItem.getAmount());
            parameterSources[i] = mapSqlParameterSource;
        }
        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }
}
