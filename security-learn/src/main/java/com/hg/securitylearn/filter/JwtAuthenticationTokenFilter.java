package com.hg.securitylearn.filter;

import com.hg.securitylearn.common.RedisCacheBean;
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
import java.util.Objects;

/**
 * 用户登陆token认真过滤器
 *
 * @author hougen
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final RedisCacheBean redisCacheBean;

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

        // 获取redis中的用户信息
        User user = redisCacheBean.getCacheObject(userId);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户信息不存在");
        }

        // TODO 用户身份权限设置
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(new LoginUser(user), null, null);
        // 将用户信息存入 securityContextHolder 中
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
