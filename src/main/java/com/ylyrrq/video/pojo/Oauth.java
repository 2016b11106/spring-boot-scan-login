package com.ylyrrq.video.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Oauth {

    private String openid;
    private String nickname;
    private String headimgurl;

}
