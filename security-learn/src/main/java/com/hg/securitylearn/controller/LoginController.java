package com.hg.securitylearn.controller;

import com.hg.securitylearn.common.Result;
import com.hg.securitylearn.model.dto.UserLoginResponse;
import com.hg.securitylearn.model.vo.UserLoginRequest;
import com.hg.securitylearn.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hougen
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserLoginService userLoginService;

    @PostMapping
    public Result<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        UserLoginResponse result = userLoginService.login(request);
        return Result.success(result);
    }
}
