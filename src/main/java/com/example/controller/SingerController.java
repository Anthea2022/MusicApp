package com.example.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.domain.Singer;
import com.example.service.SingerService;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author HP
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String name=httpServletRequest.getParameter("name").trim();
        String sex=httpServletRequest.getParameter("sex").trim();
        String pic=httpServletRequest.getParameter("pic").trim();
        String birth=httpServletRequest.getParameter("birth").trim();
        String location=httpServletRequest.getParameter("location").trim();
        String introduction=httpServletRequest.getParameter("introduction").trim();
        //把生日转换成Date形式
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate=new Date();
        try {
            birthDate=dateFormat.parse(birth);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Singer singer=new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean frag=singerService.insert(singer);
        if(frag)
        {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Object updateSinger(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        String name=httpServletRequest.getParameter("name").trim();
        String sex=httpServletRequest.getParameter("sex").trim();
        String birth=httpServletRequest.getParameter("birth").trim();
        String location=httpServletRequest.getParameter("location").trim();
        String introduction=httpServletRequest.getParameter("introduction").trim();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate=new Date();
        try {
            birthDate=dateFormat.parse(birth);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Singer singer=new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean frag=singerService.update(singer);
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


    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSinger(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        boolean frag=singerService.delete(Integer.parseInt(id));
        return frag;
    }

    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest httpServletRequest)
    {
        String id=httpServletRequest.getParameter("id").trim();
        return singerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public Object selectAll(HttpServletRequest httpServletRequest)
    {
        return singerService.selectAll();
    }

    @RequestMapping(value="/singerOfName",method = RequestMethod.GET)
    public Object singerOfName(HttpServletRequest httpServletRequest)
    {
        String name=httpServletRequest.getParameter("name").trim();
        return singerService.singerOfName("%"+name+"%");
    }

    @RequestMapping(value="singerOfSex",method = RequestMethod.GET)
    public Object singerOfSex(HttpServletRequest httpServletRequest)
    {
        String sex=httpServletRequest.getParameter("sex").trim();
        return singerService.singerOfSex(Integer.parseInt(sex));
    }

    @RequestMapping(value="updateSingerPic",method = RequestMethod.POST)
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
                +System.getProperty("file.separator")+"SingerPic";
        File file1=new File(filePath);
        //如果文件不存在，新增该路径
        if(!file1.exists())
        {
            file1.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        String avatarPath="/img/SingerPic/"+fileName;
        try {
            avatarFile.transferTo(dest);
            Singer singer=new Singer();
            singer.setId(id);
            singer.setPic(avatarPath);
            boolean frag = singerService.update(singer);
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
