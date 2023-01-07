package com.example.service.impl;

import com.example.dao.ListSongMapper;
import com.example.domain.ListSong;
import com.example.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HP
 */
@Service
public class ListSongServiceImpl implements ListSongService {
    @Autowired
    private ListSongMapper listSongMapper;


    @Override
    public boolean insert(ListSong listSong) {
        return listSongMapper.insert(listSong)>0;
    }

    @Override
    public boolean update(ListSong listSong) {
        return listSongMapper.update(listSong)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return listSongMapper.delete(id)>0;
    }

    @Override
    public boolean deleteBySongIdAndSongListId(Integer songId, Integer songListId) {
        return listSongMapper.deleteBySongIdAndSongListId(songId,songListId)>0;
    }

    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ListSong> selectAll() {
        return listSongMapper.selectAll();
    }

    @Override
    public List<ListSong> selectBySongListId(Integer songListId) {
        return listSongMapper.selectBySongListId(songListId);
    }
}
