package com.example.service.impl;

import com.example.dao.RankMapper;
import com.example.domain.Rank;
import com.example.service.RankService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {
    private final RankMapper rankMapper;

    public RankServiceImpl(RankMapper rankMapper) {
        this.rankMapper = rankMapper;
    }

    @Override
    public boolean insert(Rank rank) {
        return rankMapper.insert(rank)>0;
    }

    @Override
    public Rank selectByPrimaryKey(Integer id) {
        return rankMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Rank> selectAll() {
        return rankMapper.selectAll();
    }

    @Override
    public int selectScoreNum(Integer songListId) {
        return rankMapper.selectScoreNum(songListId);
    }

    @Override
    public int selectRankNum(Integer songListId) {
        return rankMapper.selectRankNum(songListId);
    }

    @Override
    public int avgScore(Integer songListId) {
        int personNum=rankMapper.selectRankNum(songListId);
        if(personNum==0)
        {
            return 5;
        }
        return rankMapper.selectScoreNum(songListId)/personNum;
    }
}
