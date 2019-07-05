package com.ylyrrq.video.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@TableName("loginmessage")
@Data
public class LoginMessage {
    @TableId(value ="id",type = IdType.AUTO)
    private Integer id;
    private String openid;
    private String nickname;
    private String headimgurl;
    private Date logindate;



}
