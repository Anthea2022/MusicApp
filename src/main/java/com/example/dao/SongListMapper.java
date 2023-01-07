package com.example.dao;

import com.example.domain.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anthea
 */
@Repository
public interface SongListMapper {
    public int insert(SongList songList);

    public int update(SongList songList);

    public int delete(Integer id);

    public SongList selectByPrimaryKey(Integer id);

    public List<SongList> selectAll();

    public List<SongList> selectByTitle(String title);

    public List<SongList> likeTitle(String title);

    public List<SongList> likeStyle(String style);
}
