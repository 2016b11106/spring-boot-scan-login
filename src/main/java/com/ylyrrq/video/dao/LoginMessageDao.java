package com.ylyrrq.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ylyrrq.video.pojo.LoginMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMessageDao extends BaseMapper<LoginMessage>{

    @Select("select * from loginmessage order by id desc limit 0,1")
    LoginMessage queryLastLoginMessage();


}
