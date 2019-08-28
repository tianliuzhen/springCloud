package com.example.security_aouth2_client.web;

import com.example.security_aouth2_client.config.customBase.CustomPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/27
 */
@RestController
@Slf4j
public class testController {
    /**
     *  测试 客户端 参数和 bear 两种访问形式
     * @param
     * @return
     */
    @GetMapping("/user") public Authentication getUser(Authentication authentication,
                              @RequestParam("name") String name, @RequestParam("access_token") String access_token)
    {
        System.out.println(name+":::"+access_token);
        log.info("resource: user {}", authentication);
        return authentication;
    }
    @GetMapping("/test")
    public String test(Authentication authentication){

        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return "test_string";
    }
    /**
     * 测试 客户端服务器是否能以注解形式获取用户信息
     * @param
     * @return
     */
    @RequestMapping("/getuser")
    @ResponseBody
    public CustomPrincipal getUser(@AuthenticationPrincipal CustomPrincipal CustomPrincipal) {
        return CustomPrincipal;
    }
}
