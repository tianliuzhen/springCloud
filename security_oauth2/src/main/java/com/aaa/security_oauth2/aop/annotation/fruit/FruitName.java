package com.aaa.security_oauth2.aop.annotation.fruit;


import java.lang.annotation.*;

/**
 * 水果名称注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
