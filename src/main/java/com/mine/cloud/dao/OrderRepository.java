package com.mine.cloud.dao;

import com.mine.cloud.domain.Order;

/**
 * @author CaoY
 * @date 2022-12-18 0:35
 * @description
 */
public interface OrderRepository {

    Order save(Order order);

}
