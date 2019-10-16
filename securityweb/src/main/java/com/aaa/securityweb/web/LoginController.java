package com.aaa.securityweb.web;

import com.aaa.securityweb.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * description: 该文件说明
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/6/28
 */
@Controller
@Slf4j
public class LoginController {
    @Autowired
    RedisUtil redisUtil;
    @RequestMapping("/login")
    public String userLogin(){
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
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @RequestMapping("/test")
    @ResponseBody
//    @Cacheable(value="my-redis-cache1",cacheManager = "cacheManager",keyGenerator="authkeyGenerator")
    public void test(){
        Map map=new HashMap();
        map.put(2,"d");
        map.put(1,"d");
        map.put(3,"d");
        Map map2=new HashMap();
        map2.put(2,"d");
        map2.put(1,"d");
        map2.put(3,map);
        log.info("axios测试");

        redisUtil.set("testKey", JSON.toJSONString(map2));
        throw  new RuntimeException();
//       redisUtil.set("testKey", JSONObject.toJSONString(map2));

    }
    @GetMapping("/test2")
    @ResponseBody
    @Cacheable(value="my-redis-cache2",cacheManager = "cacheManager",keyGenerator="authkeyGenerator")
    public GiftActSpec test2(){
        GiftActSpec giftActSpec=new GiftActSpec();
        giftActSpec.setActType("12");
        giftActSpec.setName("122");
//        redisUtil.set("testKey2", JSON.toJSONString(giftActSpec));
//       redisUtil.set("testKey", JSONObject.toJSONString(map2));

        return giftActSpec;
    }
}
