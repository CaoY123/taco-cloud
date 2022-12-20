package com.mine.cloud.dao;

import com.mine.cloud.domain.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author CaoY
 * @date 2022-12-20 23:14
 * @description 接入MySQL数据库以后测试配置是否成功
 */
@SpringBootTest
public class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository repository;

    @Test
    public void test1() {
        // 成功运行则表明连接成功
        repository.findAll().forEach(x -> System.out.println(x));
    }

}
