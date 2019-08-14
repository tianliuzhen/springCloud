package com.aaa.security_oauth2.web;

import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;

/**
 * description: 该文件说明
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/6/28
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String userLogin(){
        System.out.println("login");
        return "login";
    }
    @RequestMapping("/login-error")
    @ResponseBody
    public String loginError(){
        return "登录失败";
    }
    @RequestMapping("/success")
    public String loginForm(){
        return "success";
    }

    @RequestMapping("/whoim")
    @ResponseBody
    public Object whoIm(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @RequestMapping("/hello/{id}")
    @ResponseBody
    public String helloOath2(@PathVariable long id) {
        System.out.println("请求的ID编码为：" + id);
        return "helloOath2";
    }
    @RequestMapping(value = "/userInfo",method = RequestMethod.GET,consumes = MediaType.ALL_VALUE,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @SuppressWarnings("unchecked")
    public String userinfo(Principal principal) {
        return  principal.getName();
    }
}
