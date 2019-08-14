package com.aaa.security_oauth2.aop.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * description: 自定义注解
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/9
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyComponent {
    String value() default "";
}