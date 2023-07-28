package com.hg.securitylearn.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author hougen
 */
@Data
public class UserLoginRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 5748200201306071267L;
    private String username;
    private String password;
}
