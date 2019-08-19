package com.aaa.security_oauth2.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

    @Value("${site.oauth.website}")
    private String website="http://localhost:1080";

    /**
     * 登录跳转
     * @param
     * @return
     */
    @RequestMapping("/login")
    public String userLogin(){
        System.out.println("login");
        return "login";
    }
    /**
     * 登录失败
     * @param
     * @return
     */
    @RequestMapping("/login-error")
    @ResponseBody
    public String loginError(){
        return "登录失败";
    }

    /**
     * 登录成功
     * @param
     * @return
     */
    @RequestMapping("/success")
    public String loginForm(){
        // 可根据 yml进行配置
        String url="http://localhost:1080/";
        return "redirect:"+website;
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
    @GetMapping(value = "/authorize/userInfo")
    @CrossOrigin
    @ResponseBody
    public String userinfo(Principal principal) {
        return  principal.getName();
    }

    @RequestMapping("/directUrl")
    public String  directUrl(){
         return "redirect:/https://www.hao123.com/";
    }
}
