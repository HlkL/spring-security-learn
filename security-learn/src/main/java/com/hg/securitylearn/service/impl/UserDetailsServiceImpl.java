package com.hg.securitylearn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hg.securitylearn.mapper.UserMapper;
import com.hg.securitylearn.model.entity.LoginUser;
import com.hg.securitylearn.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author hougen
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询数据库用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(Objects.nonNull(username), User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }

        // 是否拥有对应的权限,设置用户相关权限
        List<String> auth = Arrays.asList("hello", "user");
        return new LoginUser(user, auth);
    }
}
