package com.mine.cloud.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CaoY
 * @date 2022-12-16 1:46
 * @description 墨西哥卷饼类
 */
@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @PrePersist
    void createdAt() {
        createdAt = new Date();
    }

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @OneToMany(targetEntity = Ingredient.class)
//    @Size(min = 1, message = "You must choose at least 1 ingrendient")
    private List<Ingredient> ingredients = new ArrayList<>();// 成分
}
