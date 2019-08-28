package com.aaa.security_oauth2.web;

import com.aaa.security_oauth2.domain.baseEntity.UserInfo;
import com.aaa.security_oauth2.util.StrUtils;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;
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
    
    /**
     *
     * @param 
     * @return 
     */
    /**
     * 用户授权信息
     * @param principal
     * @return
     */
    @ApiOperation(value = "用户授权信息")
    @RequestMapping(value = "/users/info",method = RequestMethod.GET,consumes = MediaType.ALL_VALUE,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @SuppressWarnings("unchecked")
    @ResponseBody
    public Map<String, Object> user(Principal principal, Authentication authentication) {
        OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails)authentication.getDetails();
        String tokenValue = auth2AuthenticationDetails.getTokenValue();
        String typeStr = tokenValue.split("_")[0];
        // demo_e5729d8c-cbf6-481b-a5dd-57bc70e317fd
        Map<String, Object> map = new LinkedHashMap<>();
        String key=   principal.getName();
        // TODO: 2019/8/27  模拟数据
        // 此时应根据 key 从数据库 查询用户信息
        // 此时模拟即可
        UserInfo user=new UserInfo();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("123456");
        map.put("principal", user);
        System.out.println(principal.toString());
        return map;
    }
}
