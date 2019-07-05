package com.ylyrrq.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ylyrrq.video.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface QuestionDao extends BaseMapper<Question> {
}
