package com.mine.cloud.domain;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * @author CaoY
 * @date 2022-12-16 1:23
 * @description 配料类
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    @Enumerated(EnumType.STRING)
    private final Type type;

    public static enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
}
