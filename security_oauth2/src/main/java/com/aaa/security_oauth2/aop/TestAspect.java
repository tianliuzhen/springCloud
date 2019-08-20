package com.aaa.security_oauth2.aop;

import com.aaa.security_oauth2.entity.TestDTO;
import com.aaa.security_oauth2.web.TestWebController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * description: aop切面实现拦截参数赋值
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/9
 */
@Aspect
@Component
public class TestAspect {
    @Pointcut("execution(public * com.aaa.security_oauth2.web.TestWebController.add*(..))")
    public void addAdvice(){}
    @Around("addAdvice()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Object result = null;
        Object[] args = pjp.getArgs();
        if(args != null && args.length >0) {
            String deviceId = (String) args[0];
            if(!"03".equals(deviceId)) {

//              return "no anthorization";
            }else{
                //静态模式赋值
                TestDTO user =new TestDTO();
                user.setName("01");
                TestWebController.testDTO=user ;
            }
        }
        try {
            result =pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

  /*  public static void main(String[] args) throws Exception{
      *//*  TestDTO t = new TestDTO();
        Field f = t.getClass().getDeclaredField("readOnly");
        f.setAccessible(true);
        f.set(t, "test");
        System.out.println(t.getReadOnly());*//*
        TestDTO user = new TestDTO();
        //传入属性与值均为String类型,经过ReflectUtils转换为对应属性
        ReflectUtils.setValue(user,"name","zhangsan");
        ReflectUtils.setValue(user,"isDel","false");
        ReflectUtils.setValue(user,"date","2016-10-24 21:59:06");
        System.out.println(user);
    }*/
    public static void main(String[] args) throws Exception {
        //第一种方式获取Class对象
        TestDTO stu1 = new TestDTO();//这一new 产生一个Student对象，一个Class对象。
        Class stuClass = stu1.getClass();//获取Class对象
        System.out.println(stuClass.getName());

        //第二种方式获取Class对象
        Class stuClass2 = TestDTO.class;
        System.out.println(stuClass == stuClass2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        //第三种方式获取Class对象
        try {
            Class stuClass3 = Class.forName("com.aaa.security_oauth2.entity.TestDTO");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
