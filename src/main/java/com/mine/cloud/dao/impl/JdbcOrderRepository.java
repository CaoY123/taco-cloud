package com.mine.cloud.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mine.cloud.dao.OrderRepository;
import com.mine.cloud.domain.Order;
import com.mine.cloud.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CaoY
 * @date 2022-12-18 17:03
 * @description 用Jdbc的方式实现对于Order表的存储
 */
@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order_Tacos");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            saveTacoToOrders(taco, orderId);
        }
        return order;
    }

    /**
     * 存储订单信息到表Order中
     * @param order
     * @return
     */
    private long saveOrderDetails(Order order) {
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());
//        values.remove("id");
        long orderId = orderInserter.executeAndReturnKeyHolder(values)
                .getKey().longValue();

        return orderId;
    }

    /**
     * 存储订单中信息到Taco_Order_Tacos表
     * @param taco
     * @param orderId
     * @return
     */
    private void saveTacoToOrders(Taco taco, long orderId) {
        HashMap<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);
    }

}
