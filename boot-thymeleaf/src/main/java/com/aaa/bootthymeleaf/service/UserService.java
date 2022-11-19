package com.aaa.bootthymeleaf.service;

import com.aaa.bootthymeleaf.domain.User;
import com.aaa.bootthymeleaf.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 UserService.java  2022/11/19 19:19
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUserAll() throws Exception {
        return userMapper.selectAll();
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addUser(User user) {
        userMapper.save(user);
    }

    @Override
    public User findUserById(Integer id) throws Exception {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateUserById(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}
