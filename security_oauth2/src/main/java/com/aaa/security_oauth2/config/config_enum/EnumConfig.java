package com.aaa.security_oauth2.config.config_enum;


import com.aaa.security_oauth2.config.config_enum.handlerJson.EnumCacheUtils;
import com.aaa.security_oauth2.constants.enums.Gender;
import com.aaa.security_oauth2.util.EnumUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class EnumConfig {

    /**
     * 哪个枚举都可以
     * @param
     * @return
     */
    public static final Class<?> DEFAULT = Gender.class;

    /**
     * 将某个包下的所有枚举（如果有code字段）统一添加到枚举的缓存类中
     * @param
     * @return
     */
    @PostConstruct
    public void init(){
        EnumUtils.getEnumPackageEnums(DEFAULT).entrySet().forEach(e->{
            EnumCacheUtils.build(e.getValue());
        });
    }
}

