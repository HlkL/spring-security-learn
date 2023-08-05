package com.hg.securitylearn.headler;

import com.alibaba.fastjson.JSON;
import com.hg.securitylearn.common.Result;
import com.hg.securitylearn.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权异常处理器
 *
 * @author hougen
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        Result<?> result = Result.fail(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);

    }
}