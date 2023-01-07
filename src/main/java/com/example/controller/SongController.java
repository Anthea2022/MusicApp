package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.domain.Song;
import com.example.service.SongService;
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
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest httpServletRequest, @RequestParam("file")MultipartFile multipartFile)
    {
        JSONObject jsonObject=new JSONObject();
        String singerId=httpServletRequest.getParameter("singerId").trim();
        String name=httpServletRequest.getParameter("name").trim();
        String introduction=httpServletRequest.getParameter("introduction").trim();
        String pic="/img/SongPic/yes.png";
        String lyric=httpServletRequest.getParameter("lyric").trim();
        String url=httpServletRequest.getParameter("url").trim();
        if(multipartFile.isEmpty())
        {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌曲上传失败");
            return jsonObject;
        }
        String fileName=System.currentTimeMillis()+multipartFile.getOriginalFilename();
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        File file=new File(filePath);
        if(!file.exists())
        {
            file.mkdir();
        }
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        String storeUrlPath="/song/"+fileName;
        try {
            multipartFile.transferTo(dest);
            Song song =new Song();
            song.setName(name);
            song.setSingerId(Integer.parseInt(singerId));
            song.setPic(pic);
            song.setIntroduction(introduction);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            boolean frag=songService.insert(song);
            if(frag)
            {
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"保存成功");
                jsonObject.put("avator",storeUrlPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"保存失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"保存失败");
        }finally {
            return jsonObject;
        }
    }

    @RequestMapping(value="/selectLikeName",method = RequestMethod.GET)
    public Object selectLikeName(HttpServletRequest httpServletRequest)
    {
        String name=httpServletRequest.getParameter("name").trim();
        return songService.selectLikeName("%"+name+"%");
    }

    @RequestMapping(value="/singer/detail",method = RequestMethod.GET)
    public Object selectBySingerId(HttpServletRequest httpServletRequest)
    {
        String singerId=httpServletRequest.getParameter("singerId").trim();
        return songService.selectBySingerId(Integer.parseInt(singerId));
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public Object update(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        String name=httpServletRequest.getParameter("name").trim();
        String introduction=httpServletRequest.getParameter("introduction").trim();
        String lyric=httpServletRequest.getParameter("lyric").trim();
        Song song=new Song();
        song.setId(Integer.parseInt(id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);
        boolean frag=songService.update(song);
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
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        boolean frag = songService.delete(Integer.parseInt(id));
        return frag;
    }

    @RequestMapping(value="updateSongPic",method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("avatarFile") MultipartFile avatarFile, @RequestParam("id")int id)
    {
        JSONObject jsonObject=new JSONObject();
        if(avatarFile.isEmpty())
        {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //相同的文件，重新覆盖
        String fileName=System.currentTimeMillis()+ avatarFile.getOriginalFilename();
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"SongPic";
        File file1=new File(filePath);
        //如果文件不存在，新增该路径
        if(!file1.exists())
        {
            file1.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        String avatarPath="/img/songPic/"+fileName;
        try {
            avatarFile.transferTo(dest);
            Song song=new Song();
            song.setId(id);
            song.setPic(avatarPath);
            boolean frag = songService.update(song);
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


    @RequestMapping(value="updateSongUrl",method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file") MultipartFile avatarFile, @RequestParam("id")int id)
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
        String filePath=System.getProperty("user.dir") +System.getProperty("file.separator")+"song";
        File file1=new File(filePath);
        //如果文件不存在，新增该路径
        if(!file1.exists())
        {
            file1.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        String avatarPath="/song/"+fileName;
        try {
            avatarFile.transferTo(dest);
            Song song=new Song();
            song.setId(id);
            song.setUrl(avatarPath);
            boolean frag = songService.update(song);
            if(frag)
            {
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("avatar,",avatarPath);
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
