package com.hg.securitylearn.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hg.securitylearn.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hougen
 */
@Mapper
@TableName("sys_user")
public interface UserMapper extends BaseMapper<User> {
}
