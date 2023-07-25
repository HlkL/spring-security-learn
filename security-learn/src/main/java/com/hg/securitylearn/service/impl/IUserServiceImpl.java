package com.hg.securitylearn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hg.securitylearn.mapper.UserMapper;
import com.hg.securitylearn.model.entity.User;
import com.hg.securitylearn.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hougen
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
