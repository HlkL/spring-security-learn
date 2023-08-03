package com.hg.securitylearn;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author hougen
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.hg.securitylearn.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityLearnApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityLearnApplication.class, args);
        log.info("start success");
    }
}
