package com.example.dao;

import com.example.domain.Rank;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankMapper {
    public int insert(Rank rank);

    public Rank selectByPrimaryKey(Integer id);

    public List<Rank> selectAll();

    public int selectScoreNum(Integer songListId);

    public int selectRankNum(Integer songListId);
}
