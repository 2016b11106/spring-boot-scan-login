package com.ylyrrq.video.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("danmu")
@Data
public class Danmu {
    @TableId(value = "did",type = IdType.AUTO)
    private int did;
    private String text;
    private String color;
    private int size;
    private int time;
    private int vid;
    private int position;
}
