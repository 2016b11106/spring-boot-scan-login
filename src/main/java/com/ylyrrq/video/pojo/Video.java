package com.ylyrrq.video.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("video")
@Data
public class Video {
    @TableId(value = "vid",type = IdType.AUTO)
    public int vid;
    public String lb;
    public String vname;
    public String openid;
    public String nickname;
    public String headimgurl;
    public String vaddress;
    public String vimg;
}
