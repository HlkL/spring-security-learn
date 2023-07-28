package com.hg.securitylearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author hougen
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 6443669938222510149L;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户登陆jwt token
     */
    private String token;
}
