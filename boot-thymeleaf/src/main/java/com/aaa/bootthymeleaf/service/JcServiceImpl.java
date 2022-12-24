package com.aaa.bootthymeleaf.service;

import com.aaa.bootthymeleaf.domain.JcModel;
import com.aaa.bootthymeleaf.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 UserService.java  2022/11/19 19:19
 */
@Service
public class JcServiceImpl implements JcService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<JcModel> findUserAll() throws Exception {
        return userMapper.selectAll();
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addUser(JcModel user) {
        userMapper.save(user);
    }

    @Override
    public JcModel findUserById(Integer id) throws Exception {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateUserById(JcModel user) {
        userMapper.updateByPrimaryKey(user);
    }
}
