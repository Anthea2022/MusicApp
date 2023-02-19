package com.example.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.domain.Collect;
import com.example.service.CollectService;
import com.example.utils.Consts;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/collect")
public class CollectController {
    private final CollectService collectService;

    public CollectController(CollectService collectService) {
        this.collectService = collectService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addCollect(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        String id = httpServletRequest.getParameter("id");
        String userId = httpServletRequest.getParameter("userId").trim();
        String songId = httpServletRequest.getParameter("songId").trim();
        String type = httpServletRequest.getParameter("type").trim();
        if (songId == null || songId.equals("")) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "收藏歌曲为空");
            return jsonObject;
        }
        if (collectService.collectNum(Integer.parseInt(userId), Integer.parseInt(songId))) {
            jsonObject.put(Consts.CODE, 2);
            jsonObject.put(Consts.MSG, "已收藏");
            return jsonObject;
        }
        Collect collect = new Collect();
        collect.setId(Integer.parseInt(id));
        collect.setUserId(Integer.parseInt(userId));
        collect.setSongId(Integer.parseInt(songId));
        collect.setType(new Byte(type));
        boolean frag = collectService.insert(collect);
        if (frag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "保存失敗");
        return jsonObject;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deleteCommit(HttpServletRequest httpServletRequest) {
        String id = httpServletRequest.getParameter("id").trim();
        return collectService.delete(Integer.parseInt(id));
    }

    @RequestMapping(value = "/deleteByUserIdAndSongId",method = RequestMethod.POST)
    public Object deleteByUserIdAndSongId(HttpServletRequest httpServletRequest)
    {
        String userId=httpServletRequest.getParameter("userId").trim();
        String songId=httpServletRequest.getParameter("songId").trim();
        boolean frag = collectService.deleteByUserIdAndSongId(Integer.parseInt(userId), Integer.parseInt(songId));
        return frag;
    }

    @RequestMapping(value = "/selectByUserId", method = RequestMethod.GET)
    public Object selectByUserId(HttpServletRequest httpServletRequest) {
        String userId = httpServletRequest.getParameter("userId").trim();
        return collectService.selectByUserId(Integer.parseInt(userId));
    }

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Object selectAll(HttpServletRequest httpServletRequest) {
        return collectService.selectAll();
    }

    @RequestMapping(value = "/collectNum", method = RequestMethod.GET)
    public Object collectNum(HttpServletRequest httpServletRequest) {
        String userId=httpServletRequest.getParameter("userId").trim();
        String songId = httpServletRequest.getParameter("songId").trim();
        return collectService.collectNum(Integer.parseInt(userId),Integer.parseInt(songId));
    }
}
