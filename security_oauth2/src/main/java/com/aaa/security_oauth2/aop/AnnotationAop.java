package com.aaa.security_oauth2.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * description: 该文件说明
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/10
 */
@Aspect
@Component
public class AnnotationAop {

    /*
    @Aspect  声明切面，修饰切面类，从而获得 通知。

　　　　@Before 前置通知，在目标方法开始之前执行

　　　　@AfterReturning 后置通知

　　　　@Around 环绕    注意：

        环绕通知内部一定要确保执行proceed()该方法，如果不执行该方法，业务bean中被拦截的方法就不会被执行。
        当执行该方法，如果后面还有切面的话，它的执行顺序应该是这样的：先执行后面的切面，如果后面没有切面了，
        再执行最终的目标对象的业务方法。若不执行该方法，则后面的切面，业务bean的方法都不会被执行。 
        其实我们仅使用环绕通知就可以实现前置通知、后置通知、异常通知、最终通知等的效果。

　　　　@AfterThrowing 抛出异常

　　　　@After 最终

　　　　@PointCut ，切入点，修饰方法 private void xxx(){}  之后通过“方法名”获得切入点引用
    * */

    @Pointcut("@annotation(com.aaa.security_oauth2.aop.annotation.MyMethodsComponent)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("我是方法注解");
    }
}
