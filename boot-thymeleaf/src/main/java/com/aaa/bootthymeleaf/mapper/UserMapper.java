package com.aaa.bootthymeleaf.mapper;

import com.aaa.bootthymeleaf.domain.JcModel;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author liuzhen.tian
 * @version 1.0 UserMapper.java  2022/11/19 19:21
 */

@Repository
public interface UserMapper extends Mapper<JcModel> {
}
