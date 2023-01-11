package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.service.AdminService;
import com.example.utils.Consts;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author HP
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 判断是否登录成功
     */
    @RequestMapping(value="/login/status",method= RequestMethod.POST)
    public Object loginStatus(HttpServletRequest httpServletRequest, HttpSession httpSession)
    {
        JSONObject jsonObject=new JSONObject();
        String name=httpServletRequest.getParameter("name");
        String password=httpServletRequest.getParameter("password");
        boolean frag=adminService.verifyPassword(name,password);
        if(frag)
        {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登录成功");
            httpSession.setAttribute(Consts.NAME,name);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return jsonObject;
    }
}
