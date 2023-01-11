package com.example.service;

import com.example.domain.ListSong;

import java.util.List;

/**
 * @author HP
 */
public interface ListSongService {
    public boolean insert(ListSong listSong);

    public boolean update(ListSong listSong);

    public boolean delete(Integer songId);

    public boolean deleteBySongIdAndSongListId(Integer songId,Integer songListId);

    public ListSong selectByPrimaryKey(Integer id);

    public List<ListSong> selectAll();

    public List<ListSong> selectBySongListId(Integer songListId);
}
