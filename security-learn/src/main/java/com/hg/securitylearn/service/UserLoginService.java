package com.hg.securitylearn.service;

import com.hg.securitylearn.model.dto.UserLoginResponse;
import com.hg.securitylearn.model.vo.UserLoginRequest;

/**
 * @author hougen
 */
public interface UserLoginService {
    /**
     * 用户登陆
     *
     * @param request 请求对象
     * @return {@link UserLoginResponse}
     */
    UserLoginResponse login(UserLoginRequest request);

    /**
     * 退出登陆
     *
     * @return boolean
     */
    boolean logout();
}
