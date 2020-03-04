package com.example.security_aouth2_client.web;

import com.example.security_aouth2_client.customBase.CustomPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;


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
     *  有两种 传token 的值
     *  1、 url直接绑定参数   url?token=XXXX
     *  2、直接表单传值
     * @param
     * @return
     */
    @GetMapping("/user") public Authentication getUser(Authentication authentication,
                              @RequestParam("name") String name, @RequestParam("access_token") String access_token)
    {
        //System.out.println(name+":::"+access_token);
        OAuth2AuthenticationDetails auth2AuthenticationDetails= (OAuth2AuthenticationDetails) authentication.getDetails();
        String tokenValue = auth2AuthenticationDetails.getTokenValue();
        System.out.println(tokenValue);
        //这个时候可以做一个操作，截取token前的 自定义字符 demo_
        //这里可以 模拟多租户进行操作
        String typeStr = tokenValue.split("_")[0];
        System.out.println(typeStr);
        if (StringUtils.isBlank(typeStr) ||  StringUtils.isBlank(tokenValue)) {
            throw new IllegalArgumentException("token! 无效");
        }

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
