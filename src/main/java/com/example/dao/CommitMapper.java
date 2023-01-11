package com.example.dao;

import com.example.domain.Commit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitMapper {
    public int insert(Commit commit);

    public int update(Commit commit);

    public int delete(Integer id);

    public Commit selectById(Integer id);

    public List<Commit> selectAll();

    public List<Commit> selectBySongId(Integer songId);

    public List<Commit> selectBySongListId(Integer songListId);
}
