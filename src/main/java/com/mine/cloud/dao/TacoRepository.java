package com.mine.cloud.dao;

import com.mine.cloud.domain.Taco;

/**
 * @author CaoY
 * @date 2022-12-18 0:33
 * @description Taco表数据持久化接口
 */
public interface TacoRepository {

    Taco save(Taco taco);

}
