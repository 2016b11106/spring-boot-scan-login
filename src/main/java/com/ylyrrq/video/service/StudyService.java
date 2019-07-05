package com.ylyrrq.video.service;

import com.ylyrrq.video.pojo.Study;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface StudyService {

    void saveStudy(Study study);
    Study queryStudy(String openid, Integer vid);
    void deleteStudy(String openid, Integer vid);

    List<Study> queryStudy2(String openid);
}
