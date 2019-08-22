package com.aaa.security_oauth2.config.config_orm;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * description: 描述
 *1.首先,我们先继承类 MapWrapper,重写findProperty,通过useCamelCaseMapping来判断是否开启使用驼峰
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/20
 */
public class CustomWrapper  extends MapWrapper {
    public CustomWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if(useCamelCaseMapping){
            //CaseFormat是引用的 guava库,里面有转换驼峰的,免得自己重复造轮子,pom添加
            /**
             <dependency>
             <groupId>com.google.guava</groupId>
             <artifactId>guava</artifactId>
             <version>27.1-jre</version>
             </dependency>
             **/
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,name);
        }
        return name;
    }
}
