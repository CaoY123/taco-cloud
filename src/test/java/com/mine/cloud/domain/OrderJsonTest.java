//package com.mine.cloud.domain;
//
//import cn.hutool.core.collection.CollUtil;
//import cn.hutool.core.convert.impl.CastConverter;
//import cn.hutool.core.convert.impl.ClassConverter;
//import cn.hutool.json.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.*;
//
///**
// * @author CaoY
// * @date 2022-12-21 17:18
// * @description
// */
//@SpringBootTest
//public class OrderJsonTest {
//
//    @Test
//    public void test1() {
//        List<Ingredient> ingredients1 = Arrays.asList(
//                new Ingredient("0001", "辣椒", Ingredient.Type.SAUCE),
//                new Ingredient("0002", "花椒", Ingredient.Type.WRAP),
//                new Ingredient("0003", "大料", Ingredient.Type.VEGGIES)
//        );
//
//        List<Ingredient> ingredients2 = Arrays.asList(
//                new Ingredient("0004", "猪肉", Ingredient.Type.CHEESE),
//                new Ingredient("0005", "羊肉", Ingredient.Type.PROTEIN),
//                new Ingredient("0006", "牛肉", Ingredient.Type.WRAP)
//        );
//
//        List<Taco> tacos = Arrays.asList(
//                new Taco(10001L, new Date(), "卷饼1号", ingredients1),
//                new Taco(20002L, new Date(), "卷饼2号", ingredients2)
//        );
//
//        Ingredient.Type[] types = Ingredient.Type.values();
//
//        String jsonTacos = JSONUtil.toJsonStr(tacos);
//        System.out.println("Json串转换后：");
//        System.out.println(jsonTacos);
//        JSONArray jsonArray = new JSONArray(jsonTacos);
////        System.out.println(jsonArray.toString());
//        List<JSONObject> list = JSONUtil.toList(jsonArray, JSONObject.class);
//        List<Taco> tacoList = new ArrayList<>();
//        for (JSONObject oList : list) {
//            Long id = oList.getLong("id");
//            Date createdAt = oList.getDate("createdAt");
//            String name =  oList.getStr("name");
//            JSONArray ingredientsArray = oList.getJSONArray("ingredients");
//            List<Ingredient> ingredients = new ArrayList<>();
//            for (Object ingredient : ingredientsArray) {
//                Collection<Object> values = ((JSONObject) ingredient).values();
//                Object[] objects = values.toArray();
//                String ingredientId = (String) objects[0];
//                String ingredientName = (String) objects[1];
//                String typeStr = (String) objects[2];
//                Ingredient.Type type = null;
//                for (Ingredient.Type iType : types) {
//                    if (iType.toString().equals(typeStr)) {
//                        type = iType;
//                    }
//                }
//                ingredients.add(new Ingredient(ingredientId, ingredientName, type));
//            }
//            tacoList.add(new Taco(id, createdAt, name, ingredients));
//        }
//        tacoList.forEach(System.out::println);
//
//    }
//
//}
