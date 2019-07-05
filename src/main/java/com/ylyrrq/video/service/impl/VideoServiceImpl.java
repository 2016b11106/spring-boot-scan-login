package com.ylyrrq.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ylyrrq.video.dao.VideoDao;
import com.ylyrrq.video.pojo.Danmu;
import com.ylyrrq.video.pojo.Video;
import com.ylyrrq.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;
    @Override
    public List<Video> selectByOpenid(String openid) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<Video>();
        queryWrapper.eq("openid",openid);
        List<Video> list=videoDao.selectList(queryWrapper);
        return list;
    }

    @Override
    public void saveVideo(Video video) {
        videoDao.insert(video);
    }

    @Override
    public Video queryVideoById(int vid) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<Video>();
        queryWrapper.eq("vid",vid);
        Video video = videoDao.selectOne(queryWrapper);
        return video;
    }

    @Override
    public Video selectByVid(int vid) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<Video>();
        queryWrapper.eq("vid",vid);
        Video video=videoDao.selectOne(queryWrapper);
        return video;
    }

}
