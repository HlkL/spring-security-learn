package com.hg.securitylearn.config.expression;

import com.hg.securitylearn.model.entity.LoginUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 自定义校验方法
 *
 * @author hougen
 * @since 2023/08/06 17:37
 */
@Configuration("ces")
public class CustomizeExpressionSupport {

    public boolean authority(String authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof LoginUser user) {
            return user.getPermissions().contains(authority);
        }
        return false;
    }
}
