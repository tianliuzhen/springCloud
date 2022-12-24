package com.aaa.bootthymeleaf.service;

import com.aaa.bootthymeleaf.domain.JcModel;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 JcService.java  2022/11/19 19:19
 */
public interface JcService {


    /**
     * 查询所有的用户
     * @return
     */
    List<JcModel> findUserAll()throws Exception;

    /**
     * 删除用户
     * @param id
     */
    void deleteUserById(Integer id);

    /**
     * 添加用户
     * @param user
     */
    void addUser(JcModel user) throws Exception;

    /**
     * 通过id找用户
     * @param id
     * @return
     */
    JcModel findUserById(Integer id)throws Exception;

    /**
     * 更新用户
     * @param user
     */
    void updateUserById(JcModel user)throws Exception;
}
