package com.aaa.customer.config;



import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 底层使用 OKHttp 访问配置
 * @author liuzhen.tian
 * @version 1.0 FeignClientOkHttpConfiguration.java  2020/12/17 12:57
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignClientOkHttpConfiguration {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                // 连接超时
                .connectTimeout(20, TimeUnit.SECONDS)
                // 响应超时
                .readTimeout(20, TimeUnit.SECONDS)
                // 写超时
                .writeTimeout(20, TimeUnit.SECONDS)
                // 是否自动重连
                .retryOnConnectionFailure(true)
                // 连接池
                .connectionPool(pool())
                .build();
    }
    @Bean
    public ConnectionPool pool() {
        return new ConnectionPool(50, 5, TimeUnit.MINUTES);
    }
}

