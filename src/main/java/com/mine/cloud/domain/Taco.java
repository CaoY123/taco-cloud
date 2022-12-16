package com.mine.cloud.domain;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author CaoY
 * @date 2022-12-16 1:46
 * @description 墨西哥卷饼类
 */
@Data
public class Taco {
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")

}
