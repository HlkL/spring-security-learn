package com.hg.securitylearn;

import com.hg.securitylearn.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author hougen
 */
@Slf4j
@SpringBootTest
public class SecurityLearnApplicationTest {

    @Test
    void passwordEncoderTest() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
        String encode = passwordEncoder.encode("1234");
        log.info(encode);
        // 解密比对
        boolean matches = passwordEncoder.matches("1234", encode);
        log.info(String.valueOf(matches));
    }

    @Test
    void encryptionTest() throws Exception {
        // 加密
        String jwt = JwtUtils.createJwt("1234");
        log.info(jwt);
        // 解密
        Claims claims = JwtUtils.parseJWT(jwt);
        log.info(claims.getSubject());
    }
}
