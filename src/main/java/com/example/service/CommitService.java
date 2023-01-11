package com.example.service;

import com.example.domain.Commit;

import java.util.List;

public interface CommitService {
    public boolean insert(Commit commit);

    public boolean update(Commit commit);

    public boolean delete(Integer id);

    public Commit selectById(Integer id);

    public List<Commit> selectAll();

    public List<Commit> selectBySongId(Integer songId);

    public List<Commit> selectBySongListId(Integer songListId);
}
