package com.example.dao;

import com.example.domain.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {
    public int insert(Collect collect);

    public int delete(Integer id);

    public int deleteByUserIdAndSongId(Integer userid,Integer songId);

    public List<Collect> selectByUserId(Integer UserId);

    public List<Collect> selectAll();

    public int collectNum(@Param("userId") Integer userId,@Param("songId") Integer songId);
}
