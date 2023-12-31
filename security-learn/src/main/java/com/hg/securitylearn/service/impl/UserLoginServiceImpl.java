package com.hg.securitylearn.service.impl;

import com.hg.securitylearn.model.dto.UserLoginResponse;
import com.hg.securitylearn.model.entity.LoginUser;
import com.hg.securitylearn.model.vo.UserLoginRequest;
import com.hg.securitylearn.service.UserLoginService;
import com.hg.securitylearn.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 获取用户信息
        Optional<LoginUser> userOptional = Optional.ofNullable(authenticate.getPrincipal())
                .filter(principal -> principal instanceof LoginUser)
                .map(principal -> (LoginUser) principal);

        if (userOptional.isEmpty()) {
            log.error("用户登陆失败");
            return new UserLoginResponse();
        }

        // 使用jwt加密用户id
        String token = JwtUtils.createJwt(userOptional.get().getUser().getId().toString());
        return UserLoginResponse.builder()
                .username(userOptional.get().getUsername())
                .token(token)
                .build();
    }

    @Override
    public boolean logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken auth) {
            LoginUser loginUser = (LoginUser) auth.getPrincipal();
            // 删除 redis 中的用户信息
//            return redisCacheBean.deleteObject(loginUser.getUser().getId().toString());
            return true;
        }
        return false;
    }
}
