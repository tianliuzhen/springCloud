package com.aaa.dynamic.annotation;

import java.lang.annotation.*;
/**
 * 数据源注解
 * @author seven
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface DS {
    String value() default "masterDataSource";
}
