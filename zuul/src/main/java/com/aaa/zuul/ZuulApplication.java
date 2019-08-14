package com.aaa.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableZuulProxy
@EnableEurekaClient
@EnableResourceServer
@SpringBootApplication
public class ZuulApplication {
    private static final Logger log = LoggerFactory.getLogger(ZuulApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
    @GetMapping("/user")
    @ResponseBody
    public Authentication getUser(Authentication authentication)
    {
        log.info("resource: user {}", authentication);
        return authentication;
    }
}
