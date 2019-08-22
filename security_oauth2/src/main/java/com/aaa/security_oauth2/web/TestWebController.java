package com.aaa.security_oauth2.web;

import com.aaa.security_oauth2.aop.annotation.MyMethodsComponent;
import com.aaa.security_oauth2.constants.ActObj;
import com.aaa.security_oauth2.entity.TestDTO;
import com.aaa.security_oauth2.entity.User;
import com.aaa.security_oauth2.entity.User2;
import com.aaa.security_oauth2.mapper.UserMapper;
import com.aaa.security_oauth2.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/add1")
    @MyMethodsComponent
    public String addData1(String deviceId) {
        System.out.println(testDTO);
        return "success";
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


}