package com.example.service;

import com.example.domain.SongList;

import java.util.List;

/**
 * @author HP
 */
public interface SongListService {
    public boolean insert(SongList songList);

    public boolean update(SongList songList);

    public boolean delete(Integer id);

    public SongList selectByPrimaryKey(Integer id);

    public List<SongList> selectAll();

    public List<SongList> selectByTitle(String title);

    public List<SongList> likeTitle(String title);

    public List<SongList> likeStyle(String style);
}
