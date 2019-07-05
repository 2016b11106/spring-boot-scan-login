package com.ylyrrq.video.controller;

import com.ylyrrq.video.pojo.LoginMessage;
import com.ylyrrq.video.pojo.Oauth;
import com.ylyrrq.video.service.LoginMessageService;
import com.ylyrrq.video.utils.HttpRequestUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
public class OauthController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${oauth.appid}")
    private String appid;

    @Value("${oauth.callBack}")
    private String callBack;

    @Value("${oauth.scope}")
    private String scope;

    @Value("${oauth.appsecret}")
    private String appsecret;

    @Autowired
    private LoginMessageService loginMessageService;


    @RequestMapping("/oauth")
    public String index1(Model model, HttpSession session){
        return "oauth";
    }

    @RequestMapping("/oauth/get/verify")
    @ResponseBody
    public Map<String, Object> getVerify() throws UnsupportedEncodingException {
        Map<String, Object> modelMap = new HashMap<>();
        String oauthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        String redirect_uri = URLEncoder.encode(callBack, "utf-8");
        oauthUrl =  oauthUrl.replace("APPID",appid).replace("REDIRECT_URI",redirect_uri).replace("SCOPE",scope);
        modelMap.put("oauthUrl", oauthUrl);
        return modelMap;
    }

    @RequestMapping("/callBack")
    public String callBack(String code, String state, Model model, HttpServletRequest request) throws Exception{
        logger.info("进入授权回调,code:{},state:{}",code,state);

        //1.通过code获取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        url = url.replace("APPID",appid).replace("SECRET",appsecret).replace("CODE",code);
        String tokenInfoStr =  HttpRequestUtils.httpGet(url,null,null);

        JSONObject tokenInfoObject = new JSONObject(tokenInfoStr);
        logger.info("tokenInfoObject:{}",tokenInfoObject);

        //2.通过access_token和openid获取用户信息
        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        userInfoUrl = userInfoUrl.replace("ACCESS_TOKEN",tokenInfoObject.getString("access_token")).replace("OPENID",tokenInfoObject.getString("openid"));
        String userInfoStr =  HttpRequestUtils.httpGet(userInfoUrl,null,null);
        logger.info("userInfoObject:{}",userInfoStr);

        JSONObject userInfoObject = new JSONObject(userInfoStr);
        LoginMessage loginMessage = new LoginMessage();
        loginMessage.setOpenid(userInfoObject.getString("openid"));
        loginMessage.setNickname(userInfoObject.getString("nickname"));
        loginMessage.setHeadimgurl(userInfoObject.getString("headimgurl"));
        loginMessage.setLogindate(new Date());
        loginMessageService.saveLoginMessage(loginMessage);

        model.addAttribute("tokenInfoObject",tokenInfoObject);
        model.addAttribute("userInfoObject",userInfoStr);

        return "result";
    }

    @RequestMapping("/oauth/wechat/verify")
    @ResponseBody
    public Map<String, Object> weChatVerify(HttpSession session) throws UnsupportedEncodingException {
        Map<String, Object> modelMap = new HashMap<>();

        Date date = new Date();
        LoginMessage loginMessage = loginMessageService.queryLastLoginMessage();
        long time1 = date.getTime();
        long time2 = loginMessage.getLogindate().getTime();
        long diffSecond = (time1 - time2) / 1000;

        if(diffSecond < 3){
            Oauth oauth = new Oauth();
            oauth.setOpenid(loginMessage.getOpenid());
            oauth.setNickname(loginMessage.getNickname());
            oauth.setHeadimgurl(loginMessage.getHeadimgurl());
            String encodeHeadImgUrl = URLEncoder.encode(loginMessage.getHeadimgurl(), "utf-8");
            session.setAttribute("oauth", oauth);
            session.setAttribute("encodeHeadImgUrl",encodeHeadImgUrl);
            modelMap.put("success", true);
        }
        return modelMap;
    }

    @GetMapping("/oauth/exit")
    public String exit(Model model, HttpSession session, String url) {
        session.invalidate();
        return "redirect:/index";
    }

}
