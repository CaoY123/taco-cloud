package com.mine.cloud.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author CaoY
 * @date 2022-12-17 17:30
 * @description 订单类
 *
 * 这里顺便要实现对于数据有效性的校验，
 * 包括：
 * 1. 在实体类上通过validation坐标的注解来进行数据的校验
 * 2. 在相应的Controller中进行校验，即在数据发送过来的时候也要加上相应的校验有效性的注解
 * 3. 为了使得错误的数据能及时响应给用户，要对前端的页面做修改，以捕捉信息显示给用户
 */
@Data
public class Order {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Zip code is required")
    private String zip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\\\/])([1-9][0-9])$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

}
