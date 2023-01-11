package com.example.service.impl;

import com.example.dao.CommitMapper;
import com.example.domain.Commit;
import com.example.service.CommitService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CommitServiceImpl implements CommitService {
    private final CommitMapper commitMapper;

    public CommitServiceImpl(CommitMapper commitMapper) {
        this.commitMapper = commitMapper;
    }

    @Override
    public boolean insert(Commit commit) {
        return commitMapper.insert(commit)>0;
    }

    @Override
    public boolean update(Commit commit) {
        return commitMapper.update(commit)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return commitMapper.delete(id)>0;
    }

    @Override
    public Commit selectById(Integer id) {
        return commitMapper.selectById(id);
    }

    @Override
    public List<Commit> selectAll() {
        return commitMapper.selectAll();
    }

    @Override
    public List<Commit> selectBySongId(Integer songId) {
        return commitMapper.selectBySongId(songId);
    }

    @Override
    public List<Commit> selectBySongListId(Integer songListId) {
        return commitMapper.selectBySongListId(songListId);
    }
}
