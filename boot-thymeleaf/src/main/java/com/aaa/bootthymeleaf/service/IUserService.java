package com.aaa.bootthymeleaf.service;

import com.aaa.bootthymeleaf.domain.User;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 IUserService.java  2022/11/19 19:19
 */
public interface IUserService {


    /**
     * 查询所有的用户
     * @return
     */
    List<User> findUserAll()throws Exception;

    /**
     * 删除用户
     * @param id
     */
    void deleteUserById(Integer id);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user) throws Exception;

    /**
     * 通过id找用户
     * @param id
     * @return
     */
    User findUserById(Integer id)throws Exception;

    /**
     * 更新用户
     * @param user
     */
    void updateUserById(User user)throws Exception;
}
