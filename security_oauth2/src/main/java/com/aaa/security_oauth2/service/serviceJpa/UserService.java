package com.aaa.security_oauth2.service.serviceJpa;

import com.aaa.security_oauth2.entity.User;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/23
 */
public interface UserService {


     User getUserByname(String name);

     User saveAndFlush(User user);

}
