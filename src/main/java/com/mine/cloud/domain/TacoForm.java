package com.mine.cloud.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author CaoY
 * @date 2022-12-16 1:46
 * @description 墨西哥卷饼表单类 - 用于接收以字符串格式传过来的配料的名字，
 *              后面会将这个配料表的名字映射导已存在的配料（String -> Ingredient）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TacoForm {
    private Long id;

    private Date createdAt;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @Size(min = 1, message = "You must choose at least 1 ingrendient")
    private List<String> ingredients;// 成分
}
