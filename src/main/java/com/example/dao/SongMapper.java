package com.example.dao;

import com.example.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongMapper {
    public int insert(Song song);

    public int delete(Integer id);

    public int update(Song song);

    public Song selectByPrimaryKey(Integer id);

    public List<Song> selectAll();

    public List<Song> selectLikeName(String name);

    public List<Song> selectBySingerId(Integer singerId);
}
