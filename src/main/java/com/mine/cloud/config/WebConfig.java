package com.mine.cloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author CaoY
 * @date 2022-12-17 21:34
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加视图控制器
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        // 添加一个登录页面的视图控制器 - 视图控制器的作用只是用于转发一下请求到页面上，
        // 并不作其他额外的处理
        registry.addViewController("/login");
    }
}
