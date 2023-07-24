package com.hg.securitylearn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author hougen
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SecurityLearnApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityLearnApplication.class, args);
        log.info("start success");
    }
}
