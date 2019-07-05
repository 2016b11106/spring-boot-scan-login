package com.ylyrrq.video.service;

import com.ylyrrq.video.pojo.Danmu;

import java.util.List;

public interface DanmuService {
   List<Danmu> finddanmu(int id);
   void savedanmu(Danmu danmu);
}
