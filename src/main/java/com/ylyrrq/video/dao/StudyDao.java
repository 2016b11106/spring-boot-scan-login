package com.ylyrrq.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ylyrrq.video.pojo.Study;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudyDao extends BaseMapper<Study>{

    @Select("select * from study where openid=#{openid} and vid=#{vid}")
    Study queryStudy(@Param("openid") String openid, @Param("vid") Integer vid);
}
