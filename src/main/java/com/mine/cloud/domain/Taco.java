package com.mine.cloud.domain;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author CaoY
 * @date 2022-12-16 1:46
 * @description 墨西哥卷饼类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Taco {
    private Long id;

    private Date createdAt;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @Size(min = 1, message = "You must choose at least 1 ingrendient")
    private List<Ingredient> ingredients;// 成分
}
