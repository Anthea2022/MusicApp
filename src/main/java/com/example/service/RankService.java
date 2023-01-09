package com.example.service;

import com.example.domain.Rank;

import java.util.List;

public interface RankService {
    public boolean insert(Rank rank);

    public Rank selectByPrimaryKey(Integer id);

    public List<Rank> selectAll();

    public int selectScoreNum(Integer songListId);

    public int selectRankNum(Integer songListId);

    public int avgScore(Integer songListId);
}
