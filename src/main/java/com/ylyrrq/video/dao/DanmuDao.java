package com.ylyrrq.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ylyrrq.video.pojo.Danmu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DanmuDao extends BaseMapper<Danmu> {
}
