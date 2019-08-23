package com.aaa.security_oauth2;

import com.aaa.security_oauth2.constants.enums.Gender;
import com.aaa.security_oauth2.entity.User;
import com.aaa.security_oauth2.mapper.UserMapper;
import com.aaa.security_oauth2.service.daoJpa.UserDao;
import com.aaa.security_oauth2.service.serviceJpa.UserService;
import com.aaa.security_oauth2.util.TimeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityOauth2ApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Test
    public void contextLoads() {
        userMapper.getUsers3();
    }
    /**
     * mybatis 分页查询
     * @param
     * @return
     */
    @Test
    public void findUserList() {
        int page=1; int size=10;
        // 开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(page, size); //底层实现原理采用改写语句   将下面的方法中的sql语句获取到然后做个拼接 limit  AOPjishu
        List<User> listUser = userMapper.getUsers2();
        // 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类
        PageInfo<User> pageInfoUser = new PageInfo<User>(listUser);
    }
    /**
     *  jpa 添加或更新
     * @param
     * @return
     */
    @Test
    public void jpaSaveAndFlush() {
        User user=new User();
        //如果是更新 设置主键 id 即可
        user.setId(1);
        user.setName("admin1").
                setUserName("admin1").setPassword("123456").
                setGender(Gender.FEMALE).setTel("110").
                setCreateTime(TimeUtil.dateToLocalDateTime(new Date()));
        userService.saveAndFlush(user);
    }
    /**
     * jpa 删除
     * @param
     * @return
     */
    @Test
    public void jpaDel() {
        User user=new User();
        user.setId(1);
        userDao.delete(user);
    }
    /**
     * jpa 查询
     * @param
     * @return
     */
    @Test
    public void jpaGet() {
        userService.getUserByname("admin1");
    }
}
