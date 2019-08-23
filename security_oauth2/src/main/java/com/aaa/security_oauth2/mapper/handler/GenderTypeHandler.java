package com.aaa.security_oauth2.mapper.handler;

import com.aaa.security_oauth2.config.config_enum.MyTypeHandler;
import com.aaa.security_oauth2.constants.enums.Gender;
import org.apache.ibatis.type.MappedTypes;

/**
 * description: 继承封装基类
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/22
 */
@MappedTypes({Gender.class})
public class GenderTypeHandler extends MyTypeHandler<Gender> {
}

