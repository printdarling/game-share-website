package com.wang.service.impl;

import com.wang.mapper.GameMapper;
import com.wang.service.GameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GameServiceImpl implements GameService {

    @Resource
    GameMapper gameMapper;

    @Override
    public Integer getAllGamesCount() {
        int count = gameMapper.getAllGamesCount();
        return count;
    }

    @Override
    public Integer getFreeGamesCount() {
        int count = gameMapper.getFreeGamesCount();
        return count;
    }

    @Override
    public Integer getPayGamesCount() {
        int count = gameMapper.getPayGamesCount();
        return count;
    }
}
