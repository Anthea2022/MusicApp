package com.example.service;

import com.example.domain.Song;

import java.util.List;

/**
 * @author HP
 */
public interface SongService {
    public boolean insert(Song song);

    public boolean delete(Integer id);

    public boolean update(Song song);

    public Song selectByPrimaryKey(Integer id);

    public List<Song> selectAll();

    public List<Song> selectLikeName(String name);

    public List<Song> selectBySingerId(Integer singerId);
}
