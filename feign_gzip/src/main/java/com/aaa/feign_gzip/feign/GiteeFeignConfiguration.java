package com.aaa.feign_gzip.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author laiyy
 * @date 2019/1/22 16:39
 * @description
 */
@Configuration
public class GiteeFeignConfiguration {

    /**
     * 配置 Feign 日志级别
     * <p>
     * NONE：没有日志
     * BASIC：基本日志
     * HEADERS：header
     * FULL：全部
     * <p>
     * 配置为打印全部日志，可以更方便的查看 Feign 的调用信息
     *
     * @return Feign 日志级别
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
