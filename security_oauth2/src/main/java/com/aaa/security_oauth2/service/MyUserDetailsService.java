package com.aaa.security_oauth2.service;

import com.aaa.security_oauth2.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

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
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
        if(username.equals("admin"))
        {
            // boot2.0 强制要求 密码加密 passwordEncoder
            //假设返回的用户信息如下;  模拟数据库数据
            UserInfo userInfo=new UserInfo("admin",  passwordEncoder.encode("123456"), "ROLE_ADMIN" +
                    "", true,true,true, true);
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

}
