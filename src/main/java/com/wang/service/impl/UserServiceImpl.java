package com.wang.service.impl;

import com.wang.mapper.UserMapper;
import com.wang.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public Integer getAllUserCount() {
        int count = userMapper.queryAllUserCount();
        return count;
    }

    @Override
    public Integer getAdminUserCount() {
        int count = userMapper.queryAdminUserCount();
        return count;
    }

    @Override
    public Integer getNormalUserCount() {
        int count = userMapper.queryNormalUserCount();
        return count;
    }
}
