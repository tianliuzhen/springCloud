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
 * 水果颜色注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    /**
     * 颜色枚举
     */
    public enum Color {BLUE, RED, GREEN}

    ;

    /**
     * 颜色属性
     */
    Color fruitColor() default Color.GREEN;

}