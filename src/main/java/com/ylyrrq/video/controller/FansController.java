package com.ylyrrq.video.controller;

import com.ylyrrq.video.pojo.Fans;
import com.ylyrrq.video.pojo.LoginMessage;
import com.ylyrrq.video.service.DanmuService;
import com.ylyrrq.video.service.FansService;
import com.ylyrrq.video.service.LoginMessageService;
import com.ylyrrq.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FansController {
    @Autowired
    DanmuService danmuService;
    @Autowired
    VideoService videoService;
    @Autowired
    FansService fansService;
    @Autowired
    LoginMessageService loginMessageService;
    @PostMapping("gzadd")
    @ResponseBody
    public String gzadd(@RequestBody String name){
        System.out.println(name);
        int index1=name.indexOf("=");
        int index2=name.indexOf("&");
        String openid1=name.substring((index1+1),index2);
        name=name.substring(index2);
        index2=name.indexOf("=");
        String openid2=name.substring(index2+1);
        System.out.println(openid1+"   "+openid2);
        LoginMessage loginMessage1=loginMessageService.findbyopenid1(openid1);
        LoginMessage loginMessage2=loginMessageService.findbyopenid1(openid2);
        Fans fans=new Fans();
        fans.nickname2=loginMessage2.getNickname();
        fans.nickname1=loginMessage1.getNickname();
        fans.uimg1=loginMessage1.getHeadimgurl();
        fans.uimg2=loginMessage2.getHeadimgurl();
        fans.openid1=loginMessage1.getOpenid();
        fans.openid2=loginMessage2.getOpenid();
        fansService.save(fans);
        return "123";
    }
    @PostMapping("qxgz")
    @ResponseBody
    public String qxgz(@RequestBody String name){
        System.out.println(name);
        int index1=name.indexOf("=");
        int index2=name.indexOf("&");
        String openid1=name.substring((index1+1),index2);
        name=name.substring(index2);
        index2=name.indexOf("=");
        String openid2=name.substring(index2+1);
        System.out.println(openid1+"   "+openid2);
        fansService.delete(openid1,openid2);

        return "123";
    }
}
