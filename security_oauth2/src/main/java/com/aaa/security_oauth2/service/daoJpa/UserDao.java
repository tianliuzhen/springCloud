package com.aaa.security_oauth2.service.daoJpa;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/23
 */

import com.aaa.security_oauth2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, String> {

     User findByUserName(String name);

     User saveAndFlush(User user);

}