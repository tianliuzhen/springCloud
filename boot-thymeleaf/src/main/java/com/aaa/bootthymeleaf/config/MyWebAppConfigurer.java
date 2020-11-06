package com.aaa.bootthymeleaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源映射路径
 * 创建MyWebMvcConfigurer这里的作用是:设置浏览器访问的路径对应文件真实物理路径的映射配置.
 * @author liuzhen.tian
 * @version 1.0 MyWebAppConfigurer.java  2020/10/31 23:24
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/temp-rainy/**").addResourceLocations("file:D:/temp-rainy/");
    }
}
