package com.hg.securitylearn.service.impl;

import com.hg.securitylearn.common.RedisCacheBean;
import com.hg.securitylearn.model.dto.UserLoginResponse;
import com.hg.securitylearn.model.entity.LoginUser;
import com.hg.securitylearn.model.entity.User;
import com.hg.securitylearn.model.vo.UserLoginRequest;
import com.hg.securitylearn.service.UserLoginService;
import com.hg.securitylearn.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author hougen
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {
    private final AuthenticationManager authenticationManager;
    private final RedisCacheBean redisCacheBean;


    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 获取用户信息
        Optional<User> userOptional = Optional.ofNullable(authenticate.getPrincipal())
                .filter(principal -> principal instanceof LoginUser)
                .map(principal -> (LoginUser) principal)
                .map(LoginUser::getUser);

        if (userOptional.isEmpty()) {
            log.error("用户登陆失败");
            return new UserLoginResponse();
        }

        // 使用jwt加密用户id,存放到redis中
        String token = JwtUtils.createJwt(userOptional.get().getId().toString());
        redisCacheBean.setCacheObject(userOptional.get().getId().toString(), token);

        return UserLoginResponse.builder()
                .username(userOptional.get().getUsername())
                .token(token)
                .build();
    }
}
