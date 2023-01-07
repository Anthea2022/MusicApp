package com.example.dao;

import com.example.domain.ListSong;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HP
 */
@Repository
public interface ListSongMapper {
    public int insert(ListSong listSong);

    public int update(ListSong listSong);

    public int delete(Integer id);

    public int deleteBySongIdAndSongListId(Integer songId,Integer songListId);

    public ListSong selectByPrimaryKey(Integer id);

    public List<ListSong> selectAll();

    public List<ListSong>  selectBySongListId(Integer songListId);
}
