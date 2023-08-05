package com.hg.securitylearn.filter;

import com.hg.securitylearn.mapper.MenuMapper;
import com.hg.securitylearn.mapper.UserMapper;
import com.hg.securitylearn.model.entity.LoginUser;
import com.hg.securitylearn.model.entity.User;
import com.hg.securitylearn.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * 用户登陆token认真过滤器
 *
 * @author hougen
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final UserMapper userMapper;
    private final MenuMapper menuMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 获取请求头中token信息
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            // 过滤器返回数据时直接放行
            return;
        }

        // 解析用户token
        String userId;
        try {
            Claims claims = JwtUtils.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 获取用户信息
        User user = userMapper.selectById(userId);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户信息不存在");
        }
        // 权限查询
        List<String> auth = menuMapper.selectPermsByUserId(user.getId());
        LoginUser loginUser = new LoginUser(user, auth);
        // 用户身份权限设置
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        // 将用户信息存入 securityContextHolder 中
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
