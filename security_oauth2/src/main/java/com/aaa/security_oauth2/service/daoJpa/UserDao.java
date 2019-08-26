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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserDao extends JpaRepository<User, String> {

     User findByUserName(String name);

     @Override
     User saveAndFlush(User user);

     /**
      * 也可以像mybatis 一样使用注解
      * @param id
      * @return
      */
     @Query(value = "select u from User u where u.id=:id ")
     User getUserByIdAndGender(@Param("id") Integer id);

     /**
      * pdate或delete时必须使用@Modifying对方法进行注解，才能使得ORM知道现在要执行的是写操作
      * @param ids
      * @return
      */
    @Modifying
    @Query("update User u set u.isDel = 1 where u.id in :ids")
     void deleteByIds(@Param(value = "ids") List<String> ids);
}