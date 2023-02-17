package com.mine.cloud.dao;

import com.mine.cloud.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CaoY
 * @date 2022-12-18 0:35
 * @description 订单表数据接口
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Order save(Order order);

}
