package com.hg.securitylearn.headler;

import com.alibaba.fastjson.JSON;
import com.hg.securitylearn.common.Result;
import com.hg.securitylearn.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 认证异常处理
 *
 * @author hougen
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        Result<?> result = Result.fail(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}