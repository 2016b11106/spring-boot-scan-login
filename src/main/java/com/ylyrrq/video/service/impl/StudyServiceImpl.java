package com.ylyrrq.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ylyrrq.video.dao.StudyDao;
import com.ylyrrq.video.pojo.Study;
import com.ylyrrq.video.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyServiceImpl implements StudyService{

    @Autowired
    StudyDao studyDao;

    @Override
    public void saveStudy(Study study) {
        studyDao.insert(study);
    }

    @Override
    public Study queryStudy(String openid, Integer vid) {
        return studyDao.queryStudy(openid, vid);
    }

    @Override
    public void deleteStudy(String openid, Integer vid) {
        studyDao.delete(new UpdateWrapper<Study>()
        .eq("openid", openid)
        .eq("vid", vid));
    }

    @Override
    public List<Study> queryStudy2(String openid) {
        return studyDao.selectList(new UpdateWrapper<Study>()
                .eq("openid", openid)
        );
    }

}
