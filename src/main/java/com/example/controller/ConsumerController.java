package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.domain.Consumer;
import com.example.service.ConsumerService;
import com.example.utils.Consts;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    private final ConsumerService consumerService;


    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest httpServletRequest)
    {
        JSONObject jsonObject=new JSONObject();
        String userName=httpServletRequest.getParameter("userName").trim();
        String password=httpServletRequest.getParameter("password").trim();
        String sex=httpServletRequest.getParameter("sex").trim();
        String phoneNum=httpServletRequest.getParameter("phoneNum").trim();
        String email=httpServletRequest.getParameter("email").trim();
        String birth=httpServletRequest.getParameter("birth").trim();
        String location=httpServletRequest.getParameter("location").trim();
        String introduction=httpServletRequest.getParameter("introduction").trim();
        String avatar=httpServletRequest.getParameter("avator").trim();
        if(userName==null||userName.equals(""))
        {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals(""))
        {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        //把生日转换成Date形式
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate=new Date();
        try {
            birthDate=dateFormat.parse(birth);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Consumer consumer=new Consumer();
        consumer.setUserName(userName);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvatar(avatar);
        boolean frag=consumerService.insert(consumer);
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
        String userName=httpServletRequest.getParameter("userName").trim();
        String password=httpServletRequest.getParameter("password").trim();
        String sex=httpServletRequest.getParameter("sex").trim();
        String phoneNum=httpServletRequest.getParameter("phoneNum").trim();
        String email=httpServletRequest.getParameter("email").trim();
        String birth=httpServletRequest.getParameter("birth").trim();
        String location=httpServletRequest.getParameter("location").trim();
        String introduction=httpServletRequest.getParameter("introduction").trim();
        if(userName==null||userName.equals(""))
        {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals(""))
        {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        //把生日转换成Date形式
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate=new Date();
        try {
            birthDate=dateFormat.parse(birth);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Consumer consumer=new Consumer();
        consumer.setUserName(userName);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        boolean frag=consumerService.insert(consumer);
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
        boolean frag=consumerService.delete(Integer.parseInt(id));
        return frag;
    }

    @RequestMapping(value = "/selectById",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest httpServletRequest)
    {
        String id=httpServletRequest.getParameter("id").trim();
        return consumerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public Object selectAll(HttpServletRequest httpServletRequest)
    {
        return consumerService.selectAll();
    }

    @RequestMapping(value="/selectByName",method = RequestMethod.GET)
    public Object singerOfName(HttpServletRequest httpServletRequest)
    {
        String name=httpServletRequest.getParameter("name").trim();
        return consumerService.selectByName(name);
    }

    @RequestMapping(value = "/login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req, HttpSession session){

        JSONObject jsonObject = new JSONObject();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        System.out.println(username+"  "+password);
        boolean res = consumerService.verifyPassword(username, password);

        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "登录成功");
            jsonObject.put("userMsg", consumerService.login(username));
            session.setAttribute("username", username);
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "用户名或密码错误");
            return jsonObject;
        }
    }

    @RequestMapping(value="updateConsumerPic",method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("avatarFile") MultipartFile avatarFile, @RequestParam("id")int id)
    {
        JSONObject jsonObject=new JSONObject();
        if(avatarFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }
        //相同的文件，重新覆盖
        String fileName=System.currentTimeMillis()+ avatarFile.getOriginalFilename();
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img";
        File file1=new File(filePath);
        //如果文件不存在，新增该路径
        if(!file1.exists())
        {
            file1.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        String avatarPath="/img/ConsumerPic/"+fileName;
        try {
            avatarFile.transferTo(dest);
            Consumer consumer=new Consumer();
            consumer.setId(id);
            consumer.setAvatar(avatarPath);
            boolean frag = consumerService.update(consumer);
            if(frag)
            {
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("avatar ",avatarPath);
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
