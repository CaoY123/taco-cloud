package com.mine.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * @author CaoY
 * @date 2022-12-19 22:06
 * @description
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    private DataSource dataSource;// 数据源

    /**
     * 对于下列的用户存储，只使用一种方式即可
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用内存的用户存储 - 注意密码的配置（前缀{noop}不能省）
//        auth.inMemoryAuthentication()
//                .withUser("user1")
//                .password("{noop}pass1")
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("tom")
//                .password("{noop}tom")
//                .authorities("ROLE_USER");

        // 使用jdbc的用户存储 - 在两个SQL文件中建表和加入一些用户测试数据（表：User）
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select name, password, enabled from User where name = ?"
//                )
//                .authoritiesByUsernameQuery(
//                        "select name, authority from User where name = ?"
//                );
        // 给密码加密不搞了，加上相应的passwordEncoder方法有麻烦

        // 以LDAP作为后端用户的存储
//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}") //
//                .passwordCompare()// 通过密码比对认证
//                .passwordEncoder(new BCryptPasswordEncoder()) // 指定加密策略
//                .passwordAttribute("passCode")// 指定存储密码的字段
//                .and()
//                .contextSource()// 配置LDAP服务器
//                .root("dc=tacocloud,dc=com") // 使用Spring Scurity提供的内置的服务器
//                .ldif("classpath:user.ldif")
        auth.ldapAuthentication()
                .userSearchFilter("(uid={0})")
                .contextSource()
                .url("ldap://localhost:8389/dc=tacocloud,dc=com");
    }


}
