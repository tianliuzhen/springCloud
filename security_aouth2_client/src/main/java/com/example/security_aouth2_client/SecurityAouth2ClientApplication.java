package com.example.security_aouth2_client;

import com.example.security_aouth2_client.config.CustomPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

/**
 * @EnableResourceServer  就是开启资源服务器
 * 省去了配置文件
 * @param
 * @return
 */
@SpringBootApplication
@EnableResourceServer
@RestController
public class SecurityAouth2ClientApplication {
    private static final Logger log = LoggerFactory.getLogger(SecurityAouth2ClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SecurityAouth2ClientApplication.class, args);
    }

    @GetMapping("/user") public Authentication getUser(Authentication authentication, @RequestParam("name") String name,@RequestParam("access_token") String access_token)

    {
        System.out.println(name+":::"+access_token);
        log.info("resource: user {}", authentication);
        return authentication;
    }
    @GetMapping("/test")
    public String test(Authentication authentication){
        return "test_string";
    }
    @RequestMapping("/getuser")
    @ResponseBody
    public CustomPrincipal getuser(@AuthenticationPrincipal CustomPrincipal CustomPrincipal) {
        return CustomPrincipal;
    }
}
