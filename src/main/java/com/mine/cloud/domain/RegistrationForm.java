package com.mine.cloud.domain;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author CaoY
 * @date 2022-12-20 19:21
 * @description 接收注册的表单，将其转化为用户信息存储落库，主要是将密码非明文存储
 */
@Data
public class RegistrationForm {

    private final String username;
    private final String password;
    private final String fullname;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        // 这里对注册表单提交的密码信息进行一个加密存储
        return new User(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, fullname
        );
    }

}
