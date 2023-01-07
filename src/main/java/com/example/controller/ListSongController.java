package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.domain.ListSong;
import com.example.domain.Song;
import com.example.service.ListSongService;
import com.example.service.SongService;
import com.example.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author HP
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {
    @Autowired
    private ListSongService listSongService;

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile multipartFile)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        String songId=httpServletRequest.getParameter("songId").trim();
        String songListId=httpServletRequest.getParameter("songListId").trim();
        ListSong listSong=new ListSong();
        listSong.setId(Integer.parseInt(id));
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        boolean frag = listSongService.insert(listSong);
        if(frag)
        {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"保存失败");
        return jsonObject;
    }


    @RequestMapping(value="/update",method = RequestMethod.POST)
    public Object update(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        String songId=httpServletRequest.getParameter("songId").trim();
        String songListId=httpServletRequest.getParameter("songListId").trim();
        ListSong listSong=new ListSong();
        listSong.setId(Integer.parseInt(id));
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        boolean frag=listSongService.update(listSong);
        if(frag)
        {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public Object delete(HttpServletRequest httpServletRequest)
    {
        String id=httpServletRequest.getParameter("id").trim();
        return listSongService.delete(Integer.parseInt(id));
    }

    @RequestMapping(value="/deleteBySongIdAndSongListId",method = RequestMethod.POST)
    public Object deleteBySongIdAndSongListId(HttpServletRequest httpServletRequest)
    {
        String songId=httpServletRequest.getParameter("songId").trim();
        String songListId=httpServletRequest.getParameter("songListId").trim();
        boolean frag = listSongService.deleteBySongIdAndSongListId(Integer.parseInt(songId),Integer.parseInt(songListId));
        return frag;
    }

   @RequestMapping(value="/detail",method = RequestMethod.GET)
    public Object detail(HttpServletRequest httpServletRequest)
   {
       String songListId=httpServletRequest.getParameter("songListId").trim();
       return listSongService.selectBySongListId(Integer.parseInt(songListId));
   }
}
