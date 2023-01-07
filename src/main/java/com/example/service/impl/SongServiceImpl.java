package com.example.service.impl;

import com.example.dao.SongMapper;
import com.example.domain.Song;
import com.example.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HP
 */
@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;


    @Override
    public boolean insert(Song song) {
        return songMapper.insert(song) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return songMapper.delete(id) > 0;
    }

    @Override
    public boolean update(Song song) {
        return songMapper.update(song) > 0;
    }

    @Override
    public Song selectByPrimaryKey(Integer id) {
        return songMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Song> selectAll() {
        return songMapper.selectAll();
    }

    @Override
    public List<Song> selectLikeName(String name) {
        return songMapper.selectLikeName(name);
    }

    @Override
    public List<Song> selectBySingerId(Integer singerId) {
        return songMapper.selectBySingerId(singerId);
    }
}
