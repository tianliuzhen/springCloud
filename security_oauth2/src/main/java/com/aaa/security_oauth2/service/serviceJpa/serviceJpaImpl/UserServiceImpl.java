package com.aaa.security_oauth2.service.serviceJpa.serviceJpaImpl;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/23
 */
import com.aaa.security_oauth2.entity.User;
import com.aaa.security_oauth2.service.daoJpa.UserDao;
import com.aaa.security_oauth2.service.serviceJpa.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUserByname(String name) {
        User user = userDao.findByUserName(name);
        return user;
    }

    @Override
    public User saveAndFlush(User user) {
        return userDao.saveAndFlush(user);
    }

}