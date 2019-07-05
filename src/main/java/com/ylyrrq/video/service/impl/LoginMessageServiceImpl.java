package com.ylyrrq.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ylyrrq.video.dao.LoginMessageDao;
import com.ylyrrq.video.pojo.LoginMessage;
import com.ylyrrq.video.service.LoginMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginMessageServiceImpl implements LoginMessageService {

    @Autowired
    private LoginMessageDao loginMessageDao;

    @Override
    public void saveLoginMessage(LoginMessage loginMessage) {
        loginMessageDao.insert(loginMessage);
    }

    @Override
    public LoginMessage queryLastLoginMessage() {
        return loginMessageDao.queryLastLoginMessage();
    }
    @Override
    @Cacheable(value = "LoginMessage", key = "#openid1",unless="#result == null")
    public LoginMessage findbyopenid1(String openid1) {
        QueryWrapper<LoginMessage> queryWrapper = new QueryWrapper<LoginMessage>();
        queryWrapper.eq("openid",openid1);
        queryWrapper.orderByDesc("logindate");
        System.out.println(1111111111);
        List<LoginMessage> list=loginMessageDao.selectList(queryWrapper);
        LoginMessage loginMessage=null;
        if (list!=null)
        {loginMessage=list.get(0);}
        return loginMessage;
    }



}
