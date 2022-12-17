package com.mine.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CaoY
 * @date 2022-12-14 23:45
 * @description 视图控制器
 */
//@Controller // 已经通过WebConfig添加
public class HomeController {

    @GetMapping("/")
    public String home() {
        // 返回视图名
        return "home";
    }
}
