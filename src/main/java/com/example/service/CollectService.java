package com.example.service;

import com.example.domain.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectService {
    public boolean insert(Collect collect);

    public boolean delete(Integer id);

    public boolean deleteByUserIdAndSongId(Integer userId,Integer songId);

    public List<Collect> selectByUserId(Integer userId);

    public List<Collect> selectAll();

    public boolean collectNum(@Param("userId") Integer userId,@Param("songId") Integer songId);
}
