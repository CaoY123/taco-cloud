package com.mine.cloud.dao;

import com.mine.cloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CaoY
 * @date 2022-12-17 22:45
 * @description 配料表数据库接口
 */
@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

   Iterable<Ingredient> findAll();

   Ingredient save(Ingredient ingredient);

}
