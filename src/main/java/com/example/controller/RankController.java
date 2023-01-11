package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.domain.Rank;
import com.example.service.RankService;
import com.example.utils.Consts;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rank")
public class RankController {
    private final RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }


    @RequestMapping (value="/add",method = RequestMethod.POST)
    public Object addRank(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String id=httpServletRequest.getParameter("id").trim();
        String songListId=httpServletRequest.getParameter("songListId").trim();
        String consumerId=httpServletRequest.getParameter("consumerId").trim();
        String score=httpServletRequest.getParameter("score").trim();
        Rank rank=new Rank();
        rank.setId(Integer.parseInt(id));
        rank.setSongListId(Integer.parseInt(songListId));
        rank.setConsumerId(Integer.parseInt(consumerId));
        rank.setScore(Integer.parseInt(score));
        boolean frag = rankService.insert(rank);
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


    @RequestMapping(value="/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest httpServletRequest)
    {
        String id=httpServletRequest.getParameter("id").trim();
        return rankService.selectByPrimaryKey(Integer.parseInt(id));
    }

    @RequestMapping(value="/selectAll",method = RequestMethod.GET)
    public Object selectAll(HttpServletRequest httpServletRequest)
    {
        return rankService.selectAll();
    }

    @RequestMapping(value="/selectScoreNum",method = RequestMethod.GET)
    public Object selectScoreNum(HttpServletRequest httpServletRequest)
    {
        String songListId=httpServletRequest.getParameter("songListId").trim();
        return rankService.selectScoreNum(Integer.parseInt(songListId));
    }

    @RequestMapping(value="/selectRankNum",method = RequestMethod.GET)
    public Object selectRankNum(HttpServletRequest httpServletRequest)
    {
        String songListId=httpServletRequest.getParameter("songListId").trim();
        return rankService.selectRankNum(Integer.parseInt(songListId));
    }

    @RequestMapping(value="/selectAvg",method = RequestMethod.GET)
    public Object selectAvg(HttpServletRequest httpServletRequest){
        String songListId=httpServletRequest.getParameter("songListId").trim();
        return rankService.avgScore(Integer.parseInt(songListId));
    }
}
