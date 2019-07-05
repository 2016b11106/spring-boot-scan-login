package com.ylyrrq.video.service;

import com.ylyrrq.video.pojo.LoginMessage;

public interface LoginMessageService {

    void saveLoginMessage(LoginMessage loginMessage);
    LoginMessage queryLastLoginMessage();
    LoginMessage findbyopenid1(String openid1);
}
