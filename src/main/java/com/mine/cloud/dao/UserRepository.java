package com.mine.cloud.dao;

import com.mine.cloud.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CaoY
 * @date 2022-12-20 19:01
 * @description 用户表数据接口
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // Spring Data JPA 会在运行时自动生成该接口的实现
    User findByUsername(String username);

}
