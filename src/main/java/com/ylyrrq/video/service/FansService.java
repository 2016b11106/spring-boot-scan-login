package com.ylyrrq.video.service;

import com.ylyrrq.video.pojo.Fans;

import java.util.List;

public interface FansService  {
    List<Fans> findbyopenid1(String openid1);
    List<Fans> findbyopenid2(String openid2);
    void save(Fans fans);
    void delete(String openid1, String openid2);
}
