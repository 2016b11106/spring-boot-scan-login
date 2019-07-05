package com.ylyrrq.video.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@TableName("study")
@Data
public class Study {

    @TableId(value = "sid",type = IdType.AUTO)
    private Integer sid;
    private String openid;
    private String nickname;
    private Integer vid;

}
