package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.domain.Commit;
import com.example.service.CommitService;
import com.example.utils.Consts;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/commit")
public class CommitController {
    private final CommitService commitService;

    public CommitController(CommitService commitService) {
        this.commitService = commitService;
    }

    @RequestMapping(value="/addCommit",method = RequestMethod.POST)
    public Object addCommit(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        String songId=httpServletRequest.getParameter("songId").trim();
        String songListId=httpServletRequest.getParameter("songListId").trim();
        String content=httpServletRequest.getParameter("content").trim();
        String type=httpServletRequest.getParameter("type").trim();
        Commit commit=new Commit();
        commit.setId(Integer.parseInt(id));
        commit.setSongId(Integer.parseInt(songId));
        commit.setSongListId(Integer.parseInt(songListId));
        commit.setContent(content);
        commit.setType(new Byte(type));
        boolean frag = commitService.insert(commit);
        if(frag)
        {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"保存失敗");
        return jsonObject;
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public Object updateCommit(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        String songId=httpServletRequest.getParameter("songId").trim();
        String songListId=httpServletRequest.getParameter("songListId").trim();
        String content=httpServletRequest.getParameter("content").trim();
        String type=httpServletRequest.getParameter("type").trim();
        Commit commit=new Commit();
        commit.setId(Integer.parseInt(id));
        commit.setSongId(Integer.parseInt(songId));
        commit.setSongListId(Integer.parseInt(songListId));
        commit.setContent(content);
        commit.setType(new Byte(type));
        boolean frag = commitService.update(commit);
        if(frag)
        {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失敗");
        return jsonObject;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object delete(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        boolean frag = commitService.delete(Integer.parseInt(id));
        if(frag)
        {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"刪除成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"刪除失敗");
        return jsonObject;
    }

    @RequestMapping(value = "/selectById",method = RequestMethod.GET)
    public Object selectById(HttpServletRequest httpServletRequest)
    {
        String id=httpServletRequest.getParameter("id").trim();
        return commitService.selectById(Integer.parseInt(id));
    }

    @RequestMapping(value="/selectAll",method = RequestMethod.GET)
    public Object selectAll(HttpServletRequest httpServletRequest)
    {
        return commitService.selectAll();
    }

    @RequestMapping(value="/selectBySongId",method = RequestMethod.GET)
    public Object selectBySongId(HttpServletRequest httpServletRequest)
    {
        String songId=httpServletRequest.getParameter("songId").trim();
        return commitService.selectBySongId(Integer.parseInt(songId));
    }

    @RequestMapping(value="/selectBySongListId",method = RequestMethod.GET)
    public Object selectBySongListId(HttpServletRequest httpServletRequest)
    {
        String songListId=httpServletRequest.getParameter("songListId").trim();
        return commitService.selectBySongId(Integer.parseInt(songListId));
    }

    @RequestMapping(value="/like",method = RequestMethod.POST)
    public Object like(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        String up=httpServletRequest.getParameter("up").trim();
        Commit commit=new Commit();
        commit.setId(Integer.parseInt(id));
        commit.setUp(Integer.parseInt(up));
        boolean frag = commitService.update(commit);
        if(frag)
        {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"点赞失敗");
        return jsonObject;
    }
}
