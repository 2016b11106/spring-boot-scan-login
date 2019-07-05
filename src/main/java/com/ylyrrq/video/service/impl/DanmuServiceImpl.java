package com.ylyrrq.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ylyrrq.video.dao.DanmuDao;
import com.ylyrrq.video.pojo.Danmu;
import com.ylyrrq.video.service.DanmuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DanmuServiceImpl implements DanmuService {
    @Autowired
    DanmuDao danmuDao;
    @Override
    public List<Danmu> finddanmu(int id) {
        QueryWrapper<Danmu> queryWrapper = new QueryWrapper<Danmu>();
        queryWrapper.eq("vid",id);
        List<Danmu> list=danmuDao.selectList(queryWrapper);
        return list;
    }

    @Override
    public void savedanmu(Danmu danmu) {

        danmuDao.insert(danmu);
    }
}
