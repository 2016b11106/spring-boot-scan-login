package com.ylyrrq.video.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("fans")
public class Fans {
    @TableId(value = "fid",type = IdType.AUTO)
    public int fid;
    public String nickname2;
    public String nickname1;
    public String uimg1;
    public String uimg2;
    public String openid1;
    public String openid2;

}
