package com.example.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.domain.SongList;
import com.example.service.SongListService;
import com.example.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author HP
 */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public Object addSongList(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        String title=httpServletRequest.getParameter("title").trim();
        String pic=httpServletRequest.getParameter("pic").trim();
        String introduction=httpServletRequest.getParameter("introduction").trim();
        String style=httpServletRequest.getParameter("style").trim();
        SongList songList=new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean frag = songListService.insert(songList);
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
    public Object updateSongList(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        String title=httpServletRequest.getParameter("title").trim();
        String introduction=httpServletRequest.getParameter("introduction").trim();
        String style=httpServletRequest.getParameter("style").trim();
        SongList songList=new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean frag = songListService.update(songList);
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

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public Object deleteSongList(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        boolean frag = songListService.delete(Integer.parseInt(id));
        return frag;
    }

    @RequestMapping(value="/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest httpServletRequest)
    {
        String id=httpServletRequest.getParameter("id").trim();
        return songListService.selectByPrimaryKey(Integer.parseInt(id));
    }

    @RequestMapping(value="selectAll",method = RequestMethod.GET)
    public Object selectAll(HttpServletRequest httpServletRequest)
    {
        return songListService.selectAll();
    }

    @RequestMapping(value="selectByTitle",method = RequestMethod.GET)
    public Object selectByTitle(HttpServletRequest httpServletRequest)
    {
        String title=httpServletRequest.getParameter("title").trim();
        return songListService.selectByTitle(title);
    }

    @RequestMapping(value="likeTitle",method = RequestMethod.GET)
    public Object likeTitle(HttpServletRequest httpServletRequest)
    {
        String title=httpServletRequest.getParameter("title").trim();
        return songListService.likeTitle("%"+title+"%");
    }

    @RequestMapping(value="likeStyle",method = RequestMethod.GET)
    public Object likeStyle(HttpServletRequest httpServletRequest)
    {
        String style=httpServletRequest.getParameter("style").trim();
        return songListService.likeStyle("%"+style+"%");
    }

    @RequestMapping(value="updateSongListPic",method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatarFile, @RequestParam("id")int id)
    {
        JSONObject jsonObject=new JSONObject();
        if(avatarFile.isEmpty())
        {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //相同的文件，重新覆盖
        String fileName=System.currentTimeMillis()+avatarFile.getOriginalFilename();
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"SongListPic";
        File file1=new File(filePath);
        //如果文件不存在，新增该路径
        if(!file1.exists())
        {
            file1.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        String avatarPath="/img/songListPic/"+fileName;
        try {
            avatarFile.transferTo(dest);
            SongList songList=new SongList();
            songList.setId(id);
            songList.setPic(avatarPath);
            boolean frag = songListService.update(songList);
            if(frag)
            {
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("pic",avatarPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
        } finally {
            return jsonObject;
        }
    }
}
