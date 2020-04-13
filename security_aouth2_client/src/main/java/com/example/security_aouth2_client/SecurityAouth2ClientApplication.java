package com.example.security_aouth2_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @EnableResourceServer  就是开启资源服务器
 * 省去了配置文件
 * @param
 * @return
 */
@SpringBootApplication
@EnableResourceServer
public class SecurityAouth2ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityAouth2ClientApplication.class, args);
    }


}
