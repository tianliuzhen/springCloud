package com.aaa.security_oauth2.service;

import com.aaa.security_oauth2.domain.baseEntity.UserInfo;
import com.aaa.security_oauth2.domain.baseEntity.UserInfoDetail;
import com.aaa.security_oauth2.entity.User;
import com.aaa.security_oauth2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * description: 该文件说明
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/1
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserInfoDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: 2019/8/27  模拟数据
        //这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
        //创建一个非空的Optional对象，如下：
        Optional<UserInfo> userIfo= Optional.ofNullable(userMapper.getUsersByUserName(username));
            if(userIfo.isPresent() && username.equals(userIfo.get().getUsername())) {
                // boot2.0 强制要求 密码加密 passwordEncoder
                //假设返回的用户信息如下;  模拟数据库数据
            /*    UserInfo user=new UserInfo();
                user.setId(1);
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("123456"));*/
                // 模拟权限
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(0,new SimpleGrantedAuthority("admin_role"));

                UserInfoDetail userInfo=new UserInfoDetail(userIfo.get(),authorities);
                userInfo.setAuthorities(authorities);
                return userInfo;
            }


        return null;
    }

    public static void main(String[] args) {
        String applicationToken ="test" + ":" + "$2a$10$XN7OD22T7H//Lp5dCiIPJefAuEyt2bgq.1eed5YFlF/22fNpIs06W";

        applicationToken = "Basic " + Base64.getEncoder().encodeToString(
                applicationToken.getBytes(StandardCharsets.UTF_8)).trim();
        System.out.println(applicationToken);
    }

    /**
     * isPut = true =》  从缓存中读取
     * isPut = false =》 刷新这条缓存
     * @param key
     * @param isPut
     * @return
     */
    @Cacheable(value="my-redis-cache1",condition ="#isPut" ,cacheManager = "cacheManager",keyGenerator="firstParamGenerator")
    @CachePut(value="my-redis-cache1",condition ="!#isPut" ,cacheManager = "cacheManager",keyGenerator="firstParamGenerator")
    public UserInfo getUserInfoByRedis( String key,boolean isPut) {
        return userMapper.getUsersByUserName(key);
    }

}
