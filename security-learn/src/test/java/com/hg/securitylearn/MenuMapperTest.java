package com.hg.securitylearn;

import com.hg.securitylearn.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hougen
 * @since 2023/08/05 23:36
 */
@Slf4j
@SpringBootTest
public class MenuMapperTest {
    @Resource
    private MenuMapper menuMapper;

    @Test
    void selectTest() {
        List<String> strings = menuMapper.selectPermsByUserId(1L);
        log.info(strings.toString());
    }
}
