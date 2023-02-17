package com.mine.cloud.dao;

import com.mine.cloud.domain.Ingredient;
import com.mine.cloud.domain.Taco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author CaoY
 * @date 2022-12-22 11:26
 * @description
 */
@SpringBootTest
public class TacoRepositoryTest {

    @Autowired
    private TacoRepository tacoRepository;

    List<Ingredient> ingredients1 = null;
    List<Ingredient> ingredients2 = null;
    List<Taco> tacos = null;
    @BeforeEach
    public void before() {
        ingredients1 = Arrays.asList(
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP)
        );

        ingredients2 = Arrays.asList(
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES)
        );

//        tacos = Arrays.asList(
//                new Taco(10001L, new Date(), "大卷饼1号"/*, ingredients1*/),
//                new Taco(20002L, new Date(), "大卷饼2号"/*, ingredients2*/)
//        );
    }

    @Test
    public void saveTest() {
        Taco taco = new Taco();
        taco.setId(1000L);
        taco.setName("大号卷饼1");
        taco.setCreatedAt(new Date());
        taco.setIngredients(ingredients1);
        System.out.println(taco);
        tacoRepository.save(taco);
    }

}
