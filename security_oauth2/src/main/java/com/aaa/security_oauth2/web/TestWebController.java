package com.aaa.security_oauth2.web;

import com.aaa.security_oauth2.aop.annotation.MyMethodsComponent;
import com.aaa.security_oauth2.domain.baseEntity.UserInfo;
import com.aaa.security_oauth2.entity.TestDTO;
import com.aaa.security_oauth2.entity.User;
import com.aaa.security_oauth2.entity.User2;
import com.aaa.security_oauth2.mapper.UserMapper;
import com.aaa.security_oauth2.service.baseJpa.CustomPrincipal;
import com.aaa.security_oauth2.util.RedisUtil;
import com.aaa.security_oauth2.util.StrUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * description: 该文件说明
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/9
 */
@RestController
public class TestWebController {


    public  static TestDTO testDTO;
    public  static String str;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;
    @RequestMapping("/directUrl2")
    @ResponseBody
    public User2  directUrl2(@RequestBody User2 user){
        // 在未加 反序列化之前
        /**
         * 这种方法才不是正常的 传 枚举的值
         *  {
         * "name":"1",
         * "gender":2
         * }
        * */
        /**
         * 这种方法才是正常的 传 枚举的类型
         * {
         * "name":"1",
         * "gender": "MALE"
         * }
        * */
        // 在加 反序列化之后
        /**
         * 这种方法是正常的
         *  {
         * "name":"1",
         * "gender":2
         * }
         * */
        return user;
    }

    @RequestMapping("/test")
    public  List<User> test(@RequestBody Map map ) {
//        List<Map> us= userMapper.getUsers();
//        List<User> us2=userMapper.getUsers2();
        List<User> us= userMapper.getUsers2();
        return us;
    }

    @RequestMapping("/test2")
    @ResponseBody
    @Cacheable(value="my-redis-cache1",cacheManager = "cacheManager",keyGenerator="authkeyGenerator")
    public  PageInfo<User>  test2(@RequestParam("page") int page){
        page=page==0?1:page;
        PageHelper.startPage(page, 10);
        List<User> listUser = userMapper.getUsers2();
        PageInfo<User> pageInfoUser = new PageInfo<User>(listUser);
        return pageInfoUser;

    }

    @RequestMapping("/getuser")
    @ResponseBody
    public CustomPrincipal getuser(@AuthenticationPrincipal CustomPrincipal CustomPrincipal) {
        return CustomPrincipal;
    }
    @RequestMapping("/getuser2")
    @ResponseBody
    public Object getuser2(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping("/add2")
    @MyMethodsComponent
    public String addData2(String deviceId) {
        System.out.println(testDTO);
        return "success";
    }
    @RequestMapping("/add3")
    @MyMethodsComponent
    public String addData3(String deviceId) {
        System.out.println(testDTO);
        System.out.println(str);
        System.out.println("热部署test");
        return "success";
    }
    @RequestMapping("/whoim")
    @ResponseBody
    public Object whoIm(){


        Object userDetails =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.toString());
        String str=userDetails.toString().replaceAll("UserInfoDetail\\(userInfo=UserInfo\\(","{").replaceAll("\\), authorities=null\\)","}");
        System.out.println(str);
        String jsonString = JSONObject.toJSONString(StrUtils.mapStringToMap(str));
        UserInfo javaBean = JSON.parseObject(jsonString, UserInfo.class);
        return userDetails;


    }
    public User getUser(User user) {
        User user1 = new User();
        Optional.ofNullable(user)
                .ifPresent(u->{
                    System.out.println(u);


                    user1.setName("zhangsan");

                });
        return user1;
     /*   return Optional.ofNullable(user)
                .filter(u->"zhangsan".equals(u.getName()))
                .orElseGet(()-> {
                    User user1 = new User();
                    user1.setName("zhangsan");
                    return user1;
                });*/
    }
}
