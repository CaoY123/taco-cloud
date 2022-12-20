package com.mine.cloud.config;

import com.mine.cloud.service.UserRepositoryUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                .antMatchers("/design", "/orders")
                .hasRole("USER") // 具备ROLE_USER权限的用户才能 /design 和 /orders
                .antMatchers("/", "/**")
                .permitAll()// 其他的所有的路径不管什么用户都可以访问（声明在前面规则的优先级更高）
                .and()
                .formLogin()
                .loginPage("/login")// 确定我们自定义的登录页在哪里
                .defaultSuccessUrl("/design", true);// 指定登录成功后默认跳转到的登录页，第二个参数则是让用户不管登录前访问哪个页面，都统一跳转到指定的登录成功页面

//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
    }

    @Autowired
    private DataSource dataSource;// 数据源

    @Autowired
    private UserRepositoryUserDetailService userDetailService;

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
//                .userSearchFilter("(uid={0})")
//                .contextSource()
//                .url("ldap://localhost:8389/dc=tacocloud,dc=com");

        // 自定义的用户认证
        auth.userDetailsService(userDetailService)
                .passwordEncoder(encoder());// 使用了我们下面定义的bean，会在Spring的上下文中起作用
    }

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }

}
