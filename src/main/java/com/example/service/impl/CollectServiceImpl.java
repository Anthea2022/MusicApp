package com.example.service.impl;

import com.example.dao.CollectMapper;
import com.example.domain.Collect;
import com.example.service.CollectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    private final CollectMapper collectMapper;

    public CollectServiceImpl(CollectMapper collectMapper) {
        this.collectMapper = collectMapper;
    }

    @Override
    public boolean insert(Collect collect) {
        return collectMapper.insert(collect)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return collectMapper.delete(id)>0;
    }

    @Override
    public boolean deleteByUserIdAndSongId(Integer userId, Integer songId) {
        return collectMapper.deleteByUserIdAndSongId(userId,songId)>0;
    }

    @Override
    public List<Collect> selectByUserId(Integer userId) {
        return collectMapper.selectByUserId(userId);
    }

    @Override
    public List<Collect> selectAll() {
        return collectMapper.selectAll();
    }

    @Override
    public boolean collectNum(Integer userId,Integer songId) {
        return collectMapper.collectNum(userId,songId)>0;
    }
}
