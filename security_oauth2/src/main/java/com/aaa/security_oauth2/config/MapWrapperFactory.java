package com.aaa.security_oauth2.config;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Map;

/**
 * description: 描述
 *2. 然后,我们在实现接口 ObjectWrapperFactory,通过包装工厂来创建自定义的包装类,通过hasWrapperFor判断参数不为空,并且类型是Map的时候才使用自己扩展的ObjectWrapper
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/20
 */
public class MapWrapperFactory  implements ObjectWrapperFactory {
    @Override
    public boolean hasWrapperFor(Object object) {
        return object != null && object instanceof Map;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        return new CustomWrapper(metaObject,(Map)object);
    }
}
