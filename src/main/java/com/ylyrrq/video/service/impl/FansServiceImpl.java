package com.ylyrrq.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ylyrrq.video.dao.FansDao;
import com.ylyrrq.video.pojo.Danmu;
import com.ylyrrq.video.pojo.Fans;
import com.ylyrrq.video.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FansServiceImpl implements FansService {
    @Override
    public List<Fans> findbyopenid2(String openid2) {
        QueryWrapper<Fans> queryWrapper = new QueryWrapper<Fans>();
        queryWrapper.eq("openid2",openid2);
        List<Fans> list=fansDao.selectList(queryWrapper);
        return list;
    }

    @Override
    public void save(Fans fans) {
        fansDao.insert(fans);
    }

    @Override
    public void delete(String openid1, String openid2) {
        QueryWrapper<Fans> queryWrapper=new QueryWrapper<Fans>();
        queryWrapper.eq("openid1",openid1);
        queryWrapper.eq("openid2",openid2);
        fansDao.delete(queryWrapper);

    }

    @Autowired
    FansDao fansDao;
    @Override
    public List<Fans> findbyopenid1(String openid1) {
        QueryWrapper<Fans> queryWrapper = new QueryWrapper<Fans>();
        queryWrapper.eq("openid1",openid1);
        List<Fans> list=fansDao.selectList(queryWrapper);
        return list;
    }
}
