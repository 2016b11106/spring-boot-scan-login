package com.ylyrrq.video.config;


//新增加一个类用来添加虚拟路径映射

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class MyPicConfig implements WebMvcConfigurer {

    @Override

    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/demo/**").addResourceLocations("file:C:/Users/hasee/Desktop/spring-boot-2.0-leaning-master/spring-boot-scan-login/src/main/resources/static/demo/");

    }

}

