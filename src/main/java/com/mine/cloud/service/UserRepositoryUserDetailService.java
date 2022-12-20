package com.mine.cloud.service;

import com.mine.cloud.dao.UserRepository;
import com.mine.cloud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author CaoY
 * @date 2022-12-20 19:04
 * @description 自定义的用户详情服务
 */
@Service
public class UserRepositoryUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 注：下面这个方法决不能返回null，如果查询出来的结果为null，那么抛出一个用户名未找到异常
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}
