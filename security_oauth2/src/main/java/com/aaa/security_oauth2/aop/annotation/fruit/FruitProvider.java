package com.aaa.security_oauth2.aop.annotation.fruit;

/**
 * description: 该文件说明
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/10
 */

import java.lang.annotation.*;

/**
 * 水果供应者注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
    /**
     * 供应商编号
     */
     int id() default -1;

    /**
     * 供应商名称
     */
     String name() default "";

    /**
     * 供应商地址
     */
     String address() default "";
}