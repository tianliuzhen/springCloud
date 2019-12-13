package com.aaa.dynamic.aop;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/12/13
 */
import com.aaa.dynamic.annotation.DS;
import com.aaa.dynamic.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 动态数据源AOP切面
 */
@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //切点
    @Pointcut("execution(* com.aaa.dynamic.web.*(..))")
    public void aspect() { }

    @Before("aspect()")
    private void before(JoinPoint point) {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?> classz = target.getClass();// 获取目标类
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = classz.getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(DS.class)) {
                DS data = m.getAnnotation(DS.class);
                logger.info("method :{},datasource:{}",m.getName() ,data.value());
                DataSourceContextHolder.setDB(data.value());// 数据源放到当前线程中
            }
        } catch (Exception e) {
            logger.error("get datasource error ",e);
            //默认选择master
            DataSourceContextHolder.setDB("masterDataSource");// 数据源放到当前线程中
        }
    }

    @AfterReturning("aspect()")
    public void after(JoinPoint point) {
        DataSourceContextHolder.clearDB();
    }

}
