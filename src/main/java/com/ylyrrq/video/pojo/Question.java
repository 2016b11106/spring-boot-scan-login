package com.ylyrrq.video.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("question")
@Data
public class Question {

    @TableId(value = "qid",type = IdType.AUTO)
    private Integer qid;
    private String title;
    private String content;
    private String optiona;
    private String optionb;
    private String optionc;
    private String optiond;
    private String answer;
    private String parse;
    private String suname;
    private Date createtime;
    private String nickname;

}
