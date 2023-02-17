package com.mine.cloud.dao;

import com.mine.cloud.domain.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CaoY
 * @date 2022-12-18 0:33
 * @description Taco表数据持久化接口
 */
@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {

//    Taco save(Taco taco);

}
