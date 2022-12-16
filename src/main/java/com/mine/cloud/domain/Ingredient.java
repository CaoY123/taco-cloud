package com.mine.cloud.domain;

import lombok.Data;

/**
 * @author CaoY
 * @date 2022-12-16 1:23
 * @description 配料类
 */
@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
}
