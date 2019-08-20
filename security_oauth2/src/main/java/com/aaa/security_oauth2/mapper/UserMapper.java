package com.aaa.security_oauth2.mapper;

import com.aaa.security_oauth2.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/20
 */
@Repository
//@Mapper
public interface UserMapper {

    /**
     *  mybatis
     * @param
     * @return
     */
    @Select({ " select * from sys_user " })
    List<Map> getUsers();

    @Select({ " select * from sys_user " })
    List<User> getUsers2();
}
