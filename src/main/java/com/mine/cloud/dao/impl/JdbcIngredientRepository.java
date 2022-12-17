package com.mine.cloud.dao.impl;

import com.mine.cloud.dao.IngredientRepository;
import com.mine.cloud.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author CaoY
 * @date 2022-12-17 22:53
 * @description 用Jdbc的实现对于配料的存储接口
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query("select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    @Override
    public Ingredient findById(String id) {
        return jdbcTemplate.queryForObject(
                "select id, name, type from Ingredient where id = ?",
                Ingredient.class, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        int insertCount = jdbcTemplate.update(
                "insert into Ingredient(id, name, type) values(?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return insertCount > 0 ? ingredient : null;
    }

    /**
     * 查询结果映射成Ingredient(配料类)实体类
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }
}
