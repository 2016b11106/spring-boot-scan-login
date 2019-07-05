package com.ylyrrq.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ylyrrq.video.pojo.Fans;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FansDao extends BaseMapper<Fans>{
}
