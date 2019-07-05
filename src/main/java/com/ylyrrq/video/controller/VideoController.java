package com.ylyrrq.video.controller;

import com.ylyrrq.video.dao.StudyDao;
import com.ylyrrq.video.pojo.*;
import com.ylyrrq.video.service.*;
import com.ylyrrq.video.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VideoController {
    @Autowired
    DanmuService danmuService;
    @Autowired
    StudyService studyService;
    @Autowired
    VideoService videoService;
    @Autowired
    LoginMessageService loginMessageService;
    @Autowired
    FansService fansService;
    @RequestMapping("/dd/{vid}")
    public String zhuan(Model model, @PathVariable("vid") int vid, HttpSession session){
        Oauth oauth = (Oauth) session.getAttribute("oauth");
        model.addAttribute("uimg",oauth.getHeadimgurl());
        model.addAttribute("uname",oauth.getNickname());
        model.addAttribute("videoid",vid);
        model.addAttribute("vaddress",videoService.queryVideoById(vid).getVaddress());
        return "test";
    }
    @RequestMapping("dd2")
    public String zhuan2(Model model){

        model.addAttribute("videoid",2);
        System.out.println(123);
        return "test";
    }
    @RequestMapping("/Getdanmu")
    @ResponseBody
    public String getdanmu(String danmu,String video){
        List<Danmu> list=danmuService.finddanmu(Integer.parseInt(video));
        System.out.println(list);
        System.out.println(video);
        String danmus="[";
        if (list!=null) {
            if (list.size() == 1) {
                return "[\'{ \"text\":\"" + list.get(0).getText() + "\",\"color\":\"" + list.get(0).getColor() + "\",\"size\":\"" + list.get(0).getSize() + "\",\"position\":\"" + list.get(0).getPosition() + "\",\"time\":" + list.get(0).getTime() + "}\']";
            } else{
                for (int i=0;i<list.size();i++){
                    if (i==(list.size()-1)){
                        danmus=danmus+"\'{ \"text\":\"" + list.get(i).getText() + "\",\"color\":\"" + list.get(i).getColor() + "\",\"size\":\"" + list.get(i).getSize() + "\",\"position\":\"" + list.get(i).getPosition() + "\",\"time\":" + list.get(i).getTime() + "}\']";
                    }
                    else {
                        danmus=danmus+"\'{ \"text\":\"" + list.get(i).getText() + "\",\"color\":\"" + list.get(i).getColor() + "\",\"size\":\"" + list.get(i).getSize() + "\",\"position\":\"" + list.get(i).getPosition() + "\",\"time\":" + list.get(i).getTime() + "}\',";
                    }
                }
                return danmus;
            }

        }
       return null;
    }
    @RequestMapping("/Postdanmu")
    @ResponseBody
    public String postdanmu(String danmu,String video){
        System.out.println(danmu);
       int index=danmu.indexOf(":");
       int index2=danmu.indexOf(",");
        String text=danmu.substring(index+2,index2-1);
        danmu=danmu.substring(index2+1);
         index=danmu.indexOf(":");
        index2=danmu.indexOf(",");
        String color=danmu.substring(index+2,index2-1);
        danmu=danmu.substring(index2+1);
        index=danmu.indexOf(":");
        index2=danmu.indexOf(",");
        String size=danmu.substring(index+2,index2-1);
        danmu=danmu.substring(index2+1);
        index=danmu.indexOf(":");
        index2=danmu.indexOf(",");
        String position=danmu.substring(index+2,index2-1);
        danmu=danmu.substring(index2+1);
        index=danmu.indexOf(":");
        index2=danmu.indexOf("}");
        String time=danmu.substring(index+1,index2);

        Danmu danmu1=new Danmu();
        danmu1.setText(text);
        danmu1.setColor(color);
        danmu1.setSize(Integer.parseInt(size));
        danmu1.setPosition(Integer.parseInt(position));
        danmu1.setTime(Integer.parseInt(time));
        danmu1.setVid(Integer.parseInt(video));
        danmuService.savedanmu(danmu1);
        System.out.println(video);
        return "1";
    }

    @PostMapping("/study/{vid}")
    @ResponseBody
    public Map<String, Object> study(@PathVariable("vid") Integer vid, HttpSession session){
        Map<String, Object> modelMap = new HashMap<>();
        Oauth oauth = (Oauth) session.getAttribute("oauth");
        if(oauth == null){
            modelMap.put("success", false);
            modelMap.put("msg","请先登录");
            return modelMap;
        }
        if(studyService.queryStudy(oauth.getOpenid(), vid) != null){
            modelMap.put("success", false);
            modelMap.put("msg","你已加入该课程，无需重复加入");
            return modelMap;
        }
        Study study = new Study();
        study.setOpenid(oauth.getOpenid());
        study.setNickname(oauth.getNickname());
        study.setVid(vid);
        studyService.saveStudy(study);
        modelMap.put("success", true);
        return modelMap;
    }

    @RequestMapping("/study/delete")
    @ResponseBody
    public Map<String, Object> delete(int vid, HttpSession session){
        Oauth oauth = (Oauth) session.getAttribute("oauth");
        studyService.deleteStudy(oauth.getOpenid(), vid);
        return new HashMap<>();
    }

    @GetMapping("/search/{keyword}")
    @ResponseBody
    public List<String> search(@PathVariable("keyword") String keyword){
        String keyWords[] = {"spring教程","springmvc教程","ajax教程","mybatis"};
        List<String> keyWordList = new ArrayList<>();
        for(int i = 0;i < keyWords.length;i++){
            if(keyWords[i].contains(keyword)){
                keyWordList.add(keyWords[i]);
            }
        }
        return keyWordList;
    }

    @PostMapping("/video/uploading")
    @ResponseBody
    public void upload(String lb, String vname, @RequestParam("file") MultipartFile imgFile, HttpServletRequest request){
        String filename = imgFile.getOriginalFilename();
        File fileDir = UploadUtils.getImgDirFile();
        String time = String.valueOf(System.currentTimeMillis());
        File newFile = new File(fileDir.getAbsolutePath() + File.separator + time+".mp4");
        try {
            imgFile.transferTo(newFile);
            Video video = new Video();
            video.setLb(lb);
            video.setVname(vname);
            Oauth oauth = (Oauth) request.getSession().getAttribute("oauth");
            video.setOpenid(oauth.getOpenid());
            video.setNickname(oauth.getNickname());
            video.setHeadimgurl(oauth.getHeadimgurl());
            video.setVaddress(time+".mp4");
            if(lb.equals("前端")){
                video.setVimg("demo/1.jpg");
            }else{
                video.setVimg("demo/2.jpg");
            }
            videoService.saveVideo(video);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    @RequestMapping("/test")
    public String test(Model model, String openid, HttpSession session){
        System.out.println(openid);
        /*List<Video> list=videoService.selectByOpenid("odVco1sB7ULLc3vfDe4AkIf5UWik");
        List<Fans> fans=fansService.findbyopenid1("odVco1sB7ULLc3vfDe4AkIf5UWik");
        List<Fans> gz=fansService.findbyopenid2("odVco1sB7ULLc3vfDe4AkIf5UWik");*/
        LoginMessage loginMessage=loginMessageService.findbyopenid1(openid);
        List<Video> list=videoService.selectByOpenid(openid);
        List<Fans> fans=fansService.findbyopenid1(openid);
        List<Fans> gz=fansService.findbyopenid2(openid);
        System.out.println(list.size());
        System.out.println(list);


        List<Study> studyvideo=studyService.queryStudy2(openid);
        System.out.println(studyvideo+"******");
        List<Video> studyvideos = new ArrayList<Video>();
        int i=0;
        for (Study s:studyvideo
                ) {
            studyvideos.add(videoService.selectByVid(s.getVid()));
            i++;
        }
        System.out.println(studyvideos);
        model.addAttribute("studyvideos",studyvideos);


        Oauth oauth = (Oauth) session.getAttribute("oauth");
        if (oauth.getOpenid().equals(loginMessage.getOpenid()))
            model.addAttribute("ismy",1);
        for (Fans f:fans
                ) {
            if (f.openid2.equals(oauth.getOpenid()))
                model.addAttribute("isgz",1);


        }
        model.addAttribute("user",oauth);
        model.addAttribute("gr",loginMessage);
        model.addAttribute("videos",list);
        model.addAttribute("fans",fans);
        model.addAttribute("gz",gz);
        model.addAttribute("gzcount", 0);
        if (gz!=null){
            model.addAttribute("gzcount", gz.size());
        }

        if (fans!=null) {
            model.addAttribute("fanscount", fans.size());
        }
        return "course";
    }


}
