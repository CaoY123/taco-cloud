package com.mine.cloud.dao;

import com.mine.cloud.domain.Ingredient;

/**
 * @author CaoY
 * @date 2022-12-17 22:45
 * @description 配料表数据库接口
 */
public interface IngredientRepository {

   Iterable<Ingredient> findAll();

   Ingredient findById(String id);

   Ingredient save(Ingredient ingredient);

}
