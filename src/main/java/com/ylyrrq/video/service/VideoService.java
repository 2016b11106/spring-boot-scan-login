package com.ylyrrq.video.service;

import com.ylyrrq.video.pojo.Video;

import java.util.List;

public interface VideoService {
    List<Video> selectByOpenid(String openid);
    void saveVideo(Video video);
    Video queryVideoById(int vid);

    Video selectByVid(int vid);
}
