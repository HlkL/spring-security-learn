package com.hg.securitylearn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hg.securitylearn.model.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hougen
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}